package com.infinitiasoft.projects.lovable_clone.enity;

import jakarta.persistence.ManyToOne;

import java.time.Instant;

public class ProjectFile {

    Long id;

    Project project;

    String path;

    String minioObjectKey;

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

    User createdBy;
    User updatedBy;
}
