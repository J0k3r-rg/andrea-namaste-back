package com.j0k3r.andreanamaste.whatsapp.models;

import lombok.Builder;

import java.util.List;

@Builder
public record TemplateWhatsapp(
        String name,
        Lang language,
        List<Component> components
) {}
