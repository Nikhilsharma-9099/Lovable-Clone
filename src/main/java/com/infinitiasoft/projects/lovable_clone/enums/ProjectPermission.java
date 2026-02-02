package com.infinitiasoft.projects.lovable_clone.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {
    VIEW("project:view"),
    EDIT("project:edit"),
    DELETE("project:delete"),

    MANAGE_MEMBERS("project_members:manage"),
    VIEW_MEMBERS("project_members:view"),
    EDIT_MEMBERS("project_members:edit"),
    DELETE_MEMBERS("project_members:delete");

    private final String permission;
}
