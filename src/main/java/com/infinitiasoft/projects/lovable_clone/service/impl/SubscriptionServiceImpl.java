package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.PortalResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.infinitiasoft.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId) {

    }

}
