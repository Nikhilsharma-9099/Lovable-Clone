package com.infinitiasoft.projects.lovable_clone.repository;

import com.infinitiasoft.projects.lovable_clone.enity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
