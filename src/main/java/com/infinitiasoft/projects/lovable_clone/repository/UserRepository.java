package com.infinitiasoft.projects.lovable_clone.repository;

import com.infinitiasoft.projects.lovable_clone.enity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(@NotBlank String username);
}
