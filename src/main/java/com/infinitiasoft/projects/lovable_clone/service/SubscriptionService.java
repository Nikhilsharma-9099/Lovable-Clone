package com.infinitiasoft.projects.lovable_clone.service;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.PortalResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);
}
