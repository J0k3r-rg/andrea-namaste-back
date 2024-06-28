package com.j0k3r.andreanamaste.security;

import com.j0k3r.andreanamaste.security.http.UserRequestLogin;
import com.j0k3r.andreanamaste.security.jwt.JwtService;
import com.j0k3r.andreanamaste.security.models.User;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserRequestLogin userReq) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        userReq.getUsername(),
                        userReq.getPassword());
        this.authenticationManager.authenticate(authenticationRequest).getCredentials();
        User user = this.userRepository.findByUsername(userReq.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        String token = this.jwtService.generateToken(userReq.getUsername());
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "username", user.getUsername(),
                        "role", user.getAuthorities(),
                        "id", user.getId(),
                        "email", user.getEmail()
                )
        );
    }

}
