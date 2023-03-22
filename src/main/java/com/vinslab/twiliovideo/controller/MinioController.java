package com.vinslab.twiliovideo.controller;

import com.vinslab.twiliovideo.service.MinioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MinioController {
    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @GetMapping("/upload/pre-signed")
    public ResponseEntity<String> getSecureUploadUrl(@RequestParam String filename) {
        String url = minioService.getSecurePutUrl(filename);

        return ResponseEntity.ok().body(url);
    }
}
