package com.infinitiasoft.projects.lovable_clone.service;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.PlansLimitResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlansLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
