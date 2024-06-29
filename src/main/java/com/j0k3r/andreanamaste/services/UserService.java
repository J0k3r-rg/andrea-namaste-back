package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.http.request.UserRequest;
import com.j0k3r.andreanamaste.http.response.UserResponse;
import com.j0k3r.andreanamaste.http.response.UserResponseRegistry;
import com.j0k3r.andreanamaste.security.jwt.JwtService;
import com.j0k3r.andreanamaste.security.models.User;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import com.j0k3r.andreanamaste.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public UserResponseRegistry createUser(UserRequest userRequest){
        User user = UserUtils.toUser(userRequest);
        user = userRepository.save(user);
        return UserUtils.toUserResponseRegistry(user);
    }

    @Transactional
    public UserResponse updateUser(String id, UserRequest userRequest, HttpServletRequest request){
        User user = validateIdAndToken(id, request);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setNames(userRequest.getNames());
        user.setLastnames(userRequest.getLastnames());
        user.setPhone(userRequest.getPhone());
        return UserUtils.toUserResponse(user);
    }

    @Transactional
    public void deleteUser(String id){
        userRepository.findById(id).ifPresent(user -> user.setEnable(false));
    }

    public UserResponse getUserById(String id, HttpServletRequest request){
        User user = validateIdAndToken(id, request);
        return UserUtils.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(UserUtils::toUserResponse).toList();
    }

    private User validateIdAndToken(String id, HttpServletRequest request){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        String authorization = request.getHeader("Authorization");
        String token = authorization.replace("Bearer ", "");
        String subject = jwtService.validateAndGetSubject(token);
        if(!subject.equals(user.getUsername())){
            throw new RuntimeException("Unauthorized");
        }
        return user;
    }

    public UserResponse updateUserAdmin(String id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setNames(userRequest.getNames());
        user.setLastnames(userRequest.getLastnames());
        user.setPhone(userRequest.getPhone());
        return UserUtils.toUserResponse(user);
    }

    public void sendRestorePassword(String email) {

    }

    public void resetPassword(String token, String password) {

    }

    public void activateUser(String token) {
        
    }
}
