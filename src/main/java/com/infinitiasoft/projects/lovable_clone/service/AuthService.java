package com.infinitiasoft.projects.lovable_clone.service;

import com.infinitiasoft.projects.lovable_clone.dto.auth.AuthResponse;
import com.infinitiasoft.projects.lovable_clone.dto.auth.LoginRequest;
import com.infinitiasoft.projects.lovable_clone.dto.auth.SignupRequest;
import com.infinitiasoft.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);

    UserProfileResponse getMe();
}
