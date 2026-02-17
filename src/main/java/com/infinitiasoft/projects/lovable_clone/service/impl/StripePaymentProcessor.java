package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.config.PaymentConfig;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.PortalResponse;
import com.infinitiasoft.projects.lovable_clone.enity.Plan;
import com.infinitiasoft.projects.lovable_clone.enity.User;
import com.infinitiasoft.projects.lovable_clone.enums.SubscriptionStatus;
import com.infinitiasoft.projects.lovable_clone.error.ResourceNotFoundException;
import com.infinitiasoft.projects.lovable_clone.repository.PlanRepository;
import com.infinitiasoft.projects.lovable_clone.repository.UserRepository;
import com.infinitiasoft.projects.lovable_clone.security.AuthUtil;
import com.infinitiasoft.projects.lovable_clone.service.PaymentProcessor;
import com.infinitiasoft.projects.lovable_clone.service.SubscriptionService;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionItem;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripePaymentProcessor implements PaymentProcessor {
    private final AuthUtil authUtil;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;

    @Value("${client.url}")
    private String frontEndUrl;

    @SneakyThrows
    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Plan plan = planRepository.findById(request.getPlanId()).orElseThrow(() -> new ResourceNotFoundException("Plan", request.planId().toString()));
        Long userId = authUtil.getCurrentUserId();
        User user = getUser(userId);

        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setPrice(plan.getStripePriceId()).setQuantity(1L).build())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSubscriptionData(
                        new SessionCreateParams.SubscriptionData.Builder()
                                .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                        .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE)
                                        .build())
                                .build())
                .setSuccessUrl(frontEndUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(frontEndUrl + "/cancel.html")
                .putMetadata("user_id", userId.toString())
                .putMetadata("plan_id", plan.getId().toString());

        String stripeCustomerId = user.getStripeCustomerId();
        if(stripeCustomerId == null || stripeCustomerId.isEmpty()) {
            paramsBuilder.setCustomerEmail(user.getUsername());
        }
        else {
            paramsBuilder.setCustomer(stripeCustomerId);
        }

        SessionCreateParams params = paramsBuilder.build();

        Session session = Session.create(params);
        return new CheckoutResponse(session.getUrl());
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }

    @Override
    public void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata) {
        log.debug("Stripe event type: {}", type);

        switch (type) {
            case "checkout.session.completed" -> handleCheckoutSessionCompleted((Session) stripeObject, metadata);
            case "customer.subscription.updated" -> handleCustomerSubscriptionUpdated((Subscription) stripeObject);
            case "customer.subscription.deleted" -> handleCustomerSubscriptionDeleted((Subscription) stripeObject);
            case "invoice.paid" -> handleInvoicePaid((Invoice) stripeObject);
            case "invoice.payment_failed" -> handleInvoicePaymentFailed((Invoice) stripeObject);
            default -> log.debug("Unknown event type: {}", type);
        }
    }

    private void handleCheckoutSessionCompleted(Session session, Map<String, String> metadata) {

        if(session == null) {
            log.error("Session object was null");
            return;
        }

        Long userId = Long.parseLong(metadata.get("user_id"));
        Long planId = Long.parseLong(metadata.get("plan_id"));

        String subscriptionId = session.getSubscription();
        String customerId = session.getCustomer();

        User user = getUser(userId);
        if(user.getStripeCustomerId() == null) {
            user.setStripeCustomerId(customerId);
            userRepository.save(user);
        }
        subscriptionService.activateSubscription(userId, planId, subscriptionId, customerId);

    }

    private void handleCustomerSubscriptionUpdated(Subscription subscription) {
        if(subscription == null) {
            log.error("Subscription object was null");
            return;
        }

        SubscriptionStatus status = mapStripeStatusToEnum(subscription.getStatus());
        if(status == null) {
            log.error("Unknown subscription status: {}", subscription.getStatus());
            return;
        }

        SubscriptionItem item = subscription.getItems().getData().get(0);
        Instant periodStart = toInstant(item.getCurrentPeriodStart());
        Instant periodEnd = toInstant(item.getCurrentPeriodEnd());

        Long planId = Long.resolvePlanId(item.getPlan().getId());

    }

    private void handleCustomerSubscriptionDeleted(Subscription subscription) {
    }

    private void handleInvoicePaid(Invoice invoice) {
    }

    private void handleInvoicePaymentFailed(Invoice invoice) {
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
    }

    private SubscriptionStatus mapStripeStatusToEnum(String status) {
        return switch (status) {
            case "active" -> SubscriptionStatus.ACTIVE;
            case "canceled" -> SubscriptionStatus.CANCELLED;
            case "past_due", "unpaid", "paused", "incomplete_expired" -> SubscriptionStatus.PAST_DUE;
            case "trialing" -> SubscriptionStatus.TRIALING;
            case "incomplete" -> SubscriptionStatus.INCOMPLETE;
            default -> {
                log.warn("unmapped Stripe status : {}", status);
                yield null;
            }
        };
    }

    private Instant toInstant(Long epoch) {
        return epoch != null ? Instant.ofEpochSecond(epoch) : null;
    }

}
