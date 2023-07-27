package com.java08.quanlituyendung.controller;

import com.java08.quanlituyendung.dto.*;
import com.java08.quanlituyendung.service.IPositionService;
import com.java08.quanlituyendung.service.ISkillService;

import com.java08.quanlituyendung.service.impl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    @Autowired
    ISkillService skillService;

    @GetMapping
    public ResponseEntity<SkillDTO> getSkillName(@RequestParam Long pid, @RequestParam Long id) {
        try {
            return ResponseEntity.ok(skillService.getSkillName(pid, id));
        } catch (Exception ex) {
            return ResponseEntity.ok(null);
        }
    }
}
