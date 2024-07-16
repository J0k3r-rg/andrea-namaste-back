package com.j0k3r.andreanamaste.security;

import com.j0k3r.andreanamaste.exceptions.UserException;
import com.j0k3r.andreanamaste.security.http.UserRequestLogin;
import com.j0k3r.andreanamaste.security.jwt.JwtService;
import com.j0k3r.andreanamaste.security.models.User;
import com.j0k3r.andreanamaste.security.repository.UserRepository;
import com.j0k3r.andreanamaste.security.service.AuthenticationService;
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

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserRequestLogin userReq) throws UserException {

        User user = this.userRepository.findByUsername(userReq.getUsername()).orElseThrow(
                () -> new UserException("El usuario no se encuentra registrado en la base de datos",400)
        );

        if (!user.isEnable())
            throw new UserException("El usuario no se encuentra habilitado",400);
        authenticationService.authenticate(userReq.getUsername(), userReq.getPassword());

        String token = this.jwtService.generateToken(userReq.getUsername());
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "role", user.getRole(),
                        "id", user.getId()
                )
        );
    }

}
