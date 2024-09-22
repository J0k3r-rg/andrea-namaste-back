package com.j0k3r.andreanamaste.whatsapp.models;

import lombok.Builder;

@Builder
public record RequestWhatsapp(
   String messaging_product,
   String recipient_type,
   String to,
   String type,
   TemplateWhatsapp template,
   Text text
){}

