package com.infinitiasoft.projects.lovable_clone.dto.subscription;

public record PlansLimitResponse(
        String planName,
        int maxTokensPerDay,
        int maxProject,
        boolean unlimitedAi
) {
}
