package com.example.springoauthclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class ClientController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping("/cat")
    public ResponseEntity<Resource> getCatImage(Principal principal) {
        var restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        String accessToken = authorizedClientService
                .loadAuthorizedClient("reg-client", principal.getName())
                .getAccessToken().getTokenValue();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Resource> response =
                restTemplate.exchange("http://localhost:8181/get-cat", HttpMethod.GET, entity, Resource.class);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(response.getBody());
    }
}
