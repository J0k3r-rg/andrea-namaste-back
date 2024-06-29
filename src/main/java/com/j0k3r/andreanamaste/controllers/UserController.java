package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.http.request.UserRequest;
import com.j0k3r.andreanamaste.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest userRequest) {
        System.out.println("registerUser");
        return ResponseEntity.ok("success");
//        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserRequest userRequest, HttpServletRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest,request));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("success");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id, HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUserById(id, request));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}/admin")
    public ResponseEntity<?> updateUserAdmin(@PathVariable String id, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUserAdmin(id, userRequest));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestParam(required = true, name = "token") String token) {
        userService.activateUser(token);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity<?> restorePassword(@PathVariable String email) {
        userService.sendRestorePassword(email);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam(required = true, name = "token") String token, @RequestBody String password) {
        userService.resetPassword(token, password);
        return ResponseEntity.ok("Success");
    }

}
