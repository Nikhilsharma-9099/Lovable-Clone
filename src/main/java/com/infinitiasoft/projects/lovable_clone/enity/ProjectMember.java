package com.infinitiasoft.projects.lovable_clone.enity;

import com.infinitiasoft.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public class ProjectMember {
    ProjectMemberId id;

    Project project;
    User user;
    ProjectRole role;

    Instant invitedAt;
    Instant acceptedAt;

}
