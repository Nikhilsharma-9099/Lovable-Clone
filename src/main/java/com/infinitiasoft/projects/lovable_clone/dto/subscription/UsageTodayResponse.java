package com.infinitiasoft.projects.lovable_clone.dto.subscription;

public record UsageTodayResponse(
        Integer okenUsed,
        Integer tokensLimit,
        Integer previewsRunning,
        Integer previewsList
) {
}
