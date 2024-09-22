package com.j0k3r.andreanamaste.whatsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/webhook")
public class WebhookWhatsapp {

    @Autowired
    private Environment environment;

    @GetMapping
    public ResponseEntity<String> handleWebhookVerification(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {
        String WEBHOOK_VERIFY_TOKEN = environment.getProperty("WEBHOOK_VERIFY_TOKEN");
        if ("subscribe".equals(mode) && Objects.equals(WEBHOOK_VERIFY_TOKEN, token)) {
            System.out.println("Webhook verified successfully!");
            return ResponseEntity.ok().body(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> handleIncomingMessage(@RequestBody String requestBody){
        System.out.println(requestBody);
        return ResponseEntity.ok(requestBody);
    }

}
