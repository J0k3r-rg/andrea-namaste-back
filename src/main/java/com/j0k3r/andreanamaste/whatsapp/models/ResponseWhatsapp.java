package com.j0k3r.andreanamaste.whatsapp.models;

import java.util.List;

public record ResponseWhatsapp(
        Error error,
        String messaging_product,
        List<Contact> contacts,
        List<Message> messages
) {}

record Contact(
        String input,
        String wa_id
){}

record Message(
        String id,
        String message_status
){}