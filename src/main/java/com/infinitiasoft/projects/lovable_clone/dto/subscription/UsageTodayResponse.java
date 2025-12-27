package com.infinitiasoft.projects.lovable_clone.dto.subscription;

public record UsageTodayResponse(
        int tokenUsed,
        int tokensLimit,
        int previewsRunning,
        int previewsList
) {
}
