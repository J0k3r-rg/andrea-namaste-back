package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.exceptions.ProfileItemException;
import com.j0k3r.andreanamaste.http.request.ProfileItemRequest;
import com.j0k3r.andreanamaste.http.response.ProfileItemResponse;
import com.j0k3r.andreanamaste.http.response.ProfileItemResponseClient;
import com.j0k3r.andreanamaste.models.ProfileItem;
import com.j0k3r.andreanamaste.repositories.ProfileItemRepository;
import com.j0k3r.andreanamaste.utils.ProfileItemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileItemService {

    @Autowired
    private ProfileItemRepository profileItemRepository;

    @Transactional
    public ProfileItemResponse createItemProfileName(ProfileItemRequest profileItemRequest) throws ProfileItemException {
        if(profileItemRepository.existsByName(profileItemRequest.getName())) {
            throw new ProfileItemException("Profile item already exists with name: " + profileItemRequest.getName(), 400);
        }
        ProfileItem profileItem = ProfileItemUtils.toProfileItem(profileItemRequest);
        profileItemRepository.save(profileItem);
        return ProfileItemUtils.toProfileItemResponse(profileItem);
    }

    @Transactional
    public ProfileItemResponse updateItemProfileName(String id, ProfileItemRequest profileItemRequest) throws ProfileItemException {
        ProfileItem profileItem = profileItemRepository.findById(id).orElseThrow(
                () -> new ProfileItemException("Profile item not found with id: " + id, 404)
        );
        profileItem.setName(profileItemRequest.getName());
        profileItem.setBody(profileItemRequest.getBody());
        return ProfileItemUtils.toProfileItemResponse(profileItem);
    }

    public ProfileItemResponse getItemProfileByName(String name) throws ProfileItemException {
        ProfileItem profileItem = profileItemRepository.findByName(name).orElseThrow(
                () -> new ProfileItemException("Profile item not found with name: " + name, 404)
        );
        return ProfileItemUtils.toProfileItemResponse(profileItem);
    }

    public ProfileItemResponseClient getItemProfileByNameClient(String name) throws ProfileItemException {
        ProfileItem profileItem = profileItemRepository.findByName(name).orElseThrow(
                () -> new ProfileItemException("Profile item not found with name: " + name, 404)
        );
        return ProfileItemUtils.toProfileItemResponseClient(profileItem);
    }


}
