package com.j0k3r.andreanamaste.utils;

import com.j0k3r.andreanamaste.http.request.UserRequest;
import com.j0k3r.andreanamaste.http.response.UserResponse;
import com.j0k3r.andreanamaste.http.response.UserResponseRegistry;
import com.j0k3r.andreanamaste.security.enums.Role;
import com.j0k3r.andreanamaste.security.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public static User toUser(UserRequest userRequest){
        return User.builder()
                .username(userRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .role(Role.ROLE_USER)
                .email(userRequest.getEmail())
                .names(userRequest.getNames())
                .lastnames(userRequest.getLastnames())
                .phone(userRequest.getPhone())
                .build();
    }

    public static UserResponseRegistry toUserResponseRegistry(User user){
        return UserResponseRegistry.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .names(user.getNames())
                .lastnames(user.getLastnames())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .names(user.getNames())
                .lastnames(user.getLastnames())
                .phone(user.getPhone())
                .imageUrl(user.getImageUrl())
                .build();
    }


}
