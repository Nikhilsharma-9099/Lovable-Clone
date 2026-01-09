package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.dto.auth.AuthResponse;
import com.infinitiasoft.projects.lovable_clone.dto.auth.LoginRequest;
import com.infinitiasoft.projects.lovable_clone.dto.auth.SignupRequest;
import com.infinitiasoft.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.infinitiasoft.projects.lovable_clone.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public UserProfileResponse getMe() {
        return null;
    }
}
