package com.java08.quanlituyendung.controller;

import com.java08.quanlituyendung.dto.*;
import com.java08.quanlituyendung.service.IPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
public class PositionController {

    private final IPositionService positionService;

    @GetMapping
    public ResponseEntity<ResponseObject> getPosition() {
        return positionService.getAllPosition();

    }

}
