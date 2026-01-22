package com.infinitiasoft.projects.lovable_clone.mapper;

import com.infinitiasoft.projects.lovable_clone.dto.auth.SignupRequest;
import com.infinitiasoft.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.infinitiasoft.projects.lovable_clone.enity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest request);

    UserProfileResponse toProfileResponse(User user);
}
