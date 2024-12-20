package org.example.handyman.service;

import org.example.handyman.model.Handyman;
import org.example.handyman.repository.HandyManRepo;
import org.example.handyman.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class HandyManService {
    @Autowired
    private HandyManRepo handyManRepo;
    public List<Handyman> findAll() {
        return handyManRepo.findAll();
    }
    public Handyman add(Handyman handyman,MultipartFile image) throws SQLException, IOException {
        handyman.setImage(image.getBytes());
        handyman.setImageName(image.getOriginalFilename());
        handyman.setImageType(image.getContentType());
        return handyManRepo.save(handyman);
    }
    public Handyman getHandymanById(Long id) {
        return handyManRepo.findById(id).orElse(null);
    }
    public List<Handyman> findBySkill(String skill) {
        return handyManRepo.findBySkillIgnoreCase(skill);
    }
}
