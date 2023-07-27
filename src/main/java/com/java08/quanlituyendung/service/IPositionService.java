package com.java08.quanlituyendung.service;

import com.java08.quanlituyendung.dto.PositionRequestDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.dto.SkillRequestDTO;
import com.java08.quanlituyendung.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;


public interface IPositionService {

    ResponseEntity<ResponseObject> getAllPosition();

}
