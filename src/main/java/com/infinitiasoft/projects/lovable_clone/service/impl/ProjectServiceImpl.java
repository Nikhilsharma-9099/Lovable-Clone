package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.dto.project.ProjectRequest;
import com.infinitiasoft.projects.lovable_clone.dto.project.ProjectResponse;
import com.infinitiasoft.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.infinitiasoft.projects.lovable_clone.enity.Project;
import com.infinitiasoft.projects.lovable_clone.enity.User;
import com.infinitiasoft.projects.lovable_clone.mapper.ProjectMapper;
import com.infinitiasoft.projects.lovable_clone.repository.ProjectRepository;
import com.infinitiasoft.projects.lovable_clone.repository.UserRepository;
import com.infinitiasoft.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;


    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(user)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

//        return projectRepository.findAllAccessibleByUser(userId).stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public void softDeleteProject(Long id, Long userId) {

    }
}
