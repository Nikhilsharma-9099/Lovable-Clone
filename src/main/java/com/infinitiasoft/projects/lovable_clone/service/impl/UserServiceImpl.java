package com.infinitiasoft.projects.lovable_clone.service.impl;

import com.infinitiasoft.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.infinitiasoft.projects.lovable_clone.service.UsageService;
import com.infinitiasoft.projects.lovable_clone.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long id) {
        return null;
    }
}
