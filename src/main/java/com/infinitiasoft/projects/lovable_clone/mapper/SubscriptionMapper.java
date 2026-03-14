package com.infinitiasoft.projects.lovable_clone.mapper;

import com.infinitiasoft.projects.lovable_clone.dto.subscription.PlanResponse;
import com.infinitiasoft.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.infinitiasoft.projects.lovable_clone.enity.Plan;
import com.infinitiasoft.projects.lovable_clone.enity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanResponse toPlanResponse(Plan plan);
}
