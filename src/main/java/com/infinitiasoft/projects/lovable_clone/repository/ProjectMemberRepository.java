package com.infinitiasoft.projects.lovable_clone.repository;

import com.infinitiasoft.projects.lovable_clone.enity.ProjectMember;
import com.infinitiasoft.projects.lovable_clone.enity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
    List<ProjectMember> findByIdProjectId(Long projectId);
}
