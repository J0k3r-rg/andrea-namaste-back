package com.j0k3r.andreanamaste.whatsapp.models;

import lombok.Builder;

@Builder
public record Parameter(
        String type,
        String text
){}
