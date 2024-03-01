package com.example.springresourceserver.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/get-cat")
    public ResponseEntity<Resource> getCat() {
        ClassPathResource catImage = new ClassPathResource("static/cat.jpg");

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(catImage);
    }
}
