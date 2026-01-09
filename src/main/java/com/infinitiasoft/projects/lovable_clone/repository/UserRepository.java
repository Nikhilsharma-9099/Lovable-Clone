package com.infinitiasoft.projects.lovable_clone.repository;

import com.infinitiasoft.projects.lovable_clone.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
