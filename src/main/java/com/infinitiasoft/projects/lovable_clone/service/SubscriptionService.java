package com.infinitiasoft.projects.lovable_clone.service;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.PortalResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
