package com.j0k3r.andreanamaste.whatsapp.models;

public record Error (
        String message,
        String type,
        Integer code,
        String error_subcode,
        String fbtrace_id
){}
