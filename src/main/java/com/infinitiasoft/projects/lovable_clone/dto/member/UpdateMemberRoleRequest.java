package com.infinitiasoft.projects.lovable_clone.dto.member;

import com.infinitiasoft.projects.lovable_clone.enums.ProjectRole;
import org.antlr.v4.runtime.misc.NotNull;

public record UpdateMemberRoleRequest(ProjectRole role) {

}
