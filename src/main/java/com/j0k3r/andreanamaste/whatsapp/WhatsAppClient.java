package com.j0k3r.andreanamaste.whatsapp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsAppClient {

    private String token;

    private String idClient;

    @Builder.Default
    private String version = "v20.0";

    private final String URL_BASE="https://graph.facebook.com/";

    public String getUrlFinal(){
        return this.URL_BASE+"/"+this.version+"/"+this.idClient+"/messages";
    }

}
