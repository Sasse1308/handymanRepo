package org.example.handyman.controller;

import com.sun.net.httpserver.HttpServer;
import org.example.handyman.model.Handyman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.handyman.service.HandyManService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HandyManController {
    @Autowired
    private HandyManService handyManService;
    @GetMapping("/getting")
    public List<Handyman> getHandymen() {
        return handyManService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addHandyman(@RequestPart Handyman handyman,@RequestPart MultipartFile image) throws SQLException, IOException {
        try {
            Handyman handyman1 = handyManService.add(handyman,image);
            return new ResponseEntity<>(handyman1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{imageId}/image")
    public ResponseEntity<byte[]> getHandymanImage(@PathVariable Long imageId) {
        Handyman handyman = handyManService.getHandymanById(imageId);
        byte[] imageBytes = handyman.getImage();
        String imageType = handyman.getImageType();

        // Fallback to a default content type if imageType is null or empty
        if (imageType == null || imageType.isEmpty()) {
            imageType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // Default MIME type for binary data
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageType))
                .body(imageBytes);
    }
    @GetMapping("/handyman/{id}")
    public Handyman getById(@PathVariable() Long id) {
        return handyManService.getHandymanById(id);
    }
    @GetMapping("/skill")
    public List<Handyman> getHandymenBySkill(@RequestParam String skill) {
        return handyManService.findBySkill(skill);
    }
}
