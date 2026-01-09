package com.infinitiasoft.projects.lovable_clone.dto.subscription;

public record PlansLimitResponse(
        String planName,
        Integer maxTokensPerDay,
        Integer maxProject,
        Boolean unlimitedAi
) {
}
