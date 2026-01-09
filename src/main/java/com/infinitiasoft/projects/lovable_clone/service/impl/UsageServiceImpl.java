package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.PlansLimitResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import com.infinitiasoft.projects.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlansLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
