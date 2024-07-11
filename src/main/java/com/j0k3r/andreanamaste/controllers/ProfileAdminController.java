package com.j0k3r.andreanamaste.controllers;

import com.j0k3r.andreanamaste.exceptions.ProfileItemException;
import com.j0k3r.andreanamaste.http.request.ProfileItemRequest;
import com.j0k3r.andreanamaste.http.response.ProfileItemResponse;
import com.j0k3r.andreanamaste.services.ProfileItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile/admin")
public class ProfileAdminController {

    @Autowired
    private ProfileItemService profileItemService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createItemProfileName(@RequestBody ProfileItemRequest profileItemRequest) throws ProfileItemException {
        ProfileItemResponse response = profileItemService.createItemProfileName(profileItemRequest);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{name}")
    public ResponseEntity<?> getItemProfileName(@PathVariable String name) throws ProfileItemException {
        return ResponseEntity.ok(profileItemService.getItemProfileByName(name));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemProfileName(@PathVariable String id, @RequestBody ProfileItemRequest profileItemRequest) throws ProfileItemException {
        ProfileItemResponse response = profileItemService.updateItemProfileName(id, profileItemRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/item/{name}")
    public ResponseEntity<?> getItemProfileNamePblic(@PathVariable String name) throws ProfileItemException {
        return ResponseEntity.ok(profileItemService.getItemProfileByNameClient(name));
    }

}
