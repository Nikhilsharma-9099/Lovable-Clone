package com.infinitiasoft.projects.lovable_clone.dto.project;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        com.infinitiasoft.projects.lovable_clone.enity.User owner
) {
}
