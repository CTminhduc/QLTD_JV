package com.java08.quanlituyendung.controller;

import com.java08.quanlituyendung.dto.ProfileUpdateRequestDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.service.IFileUploadService;
import com.java08.quanlituyendung.service.IUserService;
import lombok.RequiredArgsConstructor;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    @Autowired
    private IUserService userService;
    private final IFileUploadService fileUploadService;
    @GetMapping
    public ResponseEntity<ResponseObject> get(Authentication authentication) throws ParseException{
        return userService.getProfile(authentication);
    }
    @PostMapping("/upload/cv")
    public ResponseEntity<ResponseObject> uploadCV(@RequestParam("file") MultipartFile file, Authentication authentication) {
        try {
            String fileUrl = fileUploadService.uploadFile(file);
            ProfileUpdateRequestDTO profileUpdateRequestDTO = new ProfileUpdateRequestDTO();
            profileUpdateRequestDTO.setCvFile(fileUrl);
            return userService.updateProfile(profileUpdateRequestDTO ,authentication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/upload/avatar")
    public ResponseEntity<ResponseObject> uploadAvatar(@RequestParam("file") MultipartFile file, Authentication authentication) {
        try {
            String fileUrl = fileUploadService.uploadFile(file);
            ProfileUpdateRequestDTO profileUpdateRequestDTO = new ProfileUpdateRequestDTO();
            profileUpdateRequestDTO.setAvatar(fileUrl);
            return userService.updateProfile(profileUpdateRequestDTO ,authentication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping
    public ResponseEntity<ResponseObject> update(@RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO, Authentication authentication) {
                return userService.updateProfile(profileUpdateRequestDTO,authentication);
    }
}
