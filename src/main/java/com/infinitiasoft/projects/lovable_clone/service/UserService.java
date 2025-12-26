package com.infinitiasoft.projects.lovable_clone.service;

import com.infinitiasoft.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long id);
}
