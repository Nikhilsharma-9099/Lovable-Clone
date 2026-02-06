package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.config.PaymentConfig;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.PortalResponse;
import com.infinitiasoft.projects.lovable_clone.enity.Plan;
import com.infinitiasoft.projects.lovable_clone.enity.User;
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
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

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
        log.info("type");
    }
}
