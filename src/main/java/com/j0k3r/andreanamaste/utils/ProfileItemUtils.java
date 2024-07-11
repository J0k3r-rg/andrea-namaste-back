package com.j0k3r.andreanamaste.utils;

import com.j0k3r.andreanamaste.http.request.ProfileItemRequest;
import com.j0k3r.andreanamaste.http.response.ProfileItemResponse;
import com.j0k3r.andreanamaste.http.response.ProfileItemResponseClient;
import com.j0k3r.andreanamaste.models.ProfileItem;
import org.springframework.stereotype.Component;

@Component
public class ProfileItemUtils {

    public static ProfileItem toProfileItem(ProfileItemRequest profileItemRequest){
        return ProfileItem.builder()
                .name(profileItemRequest.getName())
                .body(profileItemRequest.getBody())
                .build();
    }

    public static ProfileItemResponse toProfileItemResponse(ProfileItem profileItem){
        return ProfileItemResponse.builder()
                .id(profileItem.getId())
                .name(profileItem.getName())
                .body(profileItem.getBody())
                .build();
    }

    public static ProfileItemResponseClient toProfileItemResponseClient(ProfileItem profileItem){
        return ProfileItemResponseClient.builder()
                .name(profileItem.getName())
                .body(profileItem.getBody())
                .build();
    }

}
