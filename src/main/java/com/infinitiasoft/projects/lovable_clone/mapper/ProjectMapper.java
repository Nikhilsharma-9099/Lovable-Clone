package com.infinitiasoft.projects.lovable_clone.mapper;

import com.infinitiasoft.projects.lovable_clone.dto.project.ProjectResponse;
import com.infinitiasoft.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.infinitiasoft.projects.lovable_clone.enity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    @Mapping(source = "name", target = "projectName")
    @Mapping(target = "createdAt", dateFormat = "YYYY-MM-DD")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);
}
