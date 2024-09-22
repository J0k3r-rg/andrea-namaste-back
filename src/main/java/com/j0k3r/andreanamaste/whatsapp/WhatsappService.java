package com.j0k3r.andreanamaste.whatsapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0k3r.andreanamaste.whatsapp.models.RequestWhatsapp;
import com.j0k3r.andreanamaste.whatsapp.models.ResponseWhatsapp;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class WhatsappService {

    private final RestClient restClient;

    public WhatsappService(WhatsAppClient client){
        restClient = RestClient.builder()
                .baseUrl(client.getUrlFinal())
                .defaultHeader("Authorization", "Bearer "+client.getToken())
                .build();
    }

    public ResponseWhatsapp sendMessage(RequestWhatsapp requestWhatsapp){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String response = restClient.post()
                    .uri("")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestWhatsapp)
                    .retrieve()
                    .body(String.class);
            return objectMapper.readValue(response, ResponseWhatsapp.class);
        } catch (Exception e) {
            try {
                return objectMapper.readValue(e.getMessage().substring(19, e.getMessage().length() - 1), ResponseWhatsapp.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
