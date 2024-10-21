package org.example.nestcomm.controllers;

import org.example.nestcomm.models.ProductImage;
import org.example.nestcomm.repositories.ProductImageRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.ByteArrayInputStream;

@RestController

public class ProductImageController {
    private final ProductImageRepositoryInt imageRepository;

    @Autowired
    public ProductImageController( ProductImageRepositoryInt productImageRepositoryInt) {
        this.imageRepository = productImageRepositoryInt;
    }

    @GetMapping("/image/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        ProductImage image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getData())));
    }
}
