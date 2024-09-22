package com.j0k3r.andreanamaste.whatsapp.models;

import lombok.Builder;

import java.util.List;

@Builder
public record Component(
        String type,
        List<Parameter> parameters
) {}
