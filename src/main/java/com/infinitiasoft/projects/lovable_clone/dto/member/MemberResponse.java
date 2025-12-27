package com.infinitiasoft.projects.lovable_clone.dto.member;

import com.infinitiasoft.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String name,
        String email,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt
) {
}
