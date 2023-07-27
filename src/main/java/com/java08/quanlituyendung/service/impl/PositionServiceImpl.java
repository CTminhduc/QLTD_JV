package com.java08.quanlituyendung.service.impl;

import com.java08.quanlituyendung.converter.PositionConverter;
import com.java08.quanlituyendung.dto.PositionRequestDTO;
import com.java08.quanlituyendung.dto.ResponseDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.entity.*;
import com.java08.quanlituyendung.repository.*;
import com.java08.quanlituyendung.service.IPositionService;

import java.util.ArrayList;
import java.util.List;

import com.java08.quanlituyendung.utils.Constant;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements IPositionService {
        @Autowired
        private final QuestionRepository questionRepository;
        @Autowired
        private final SkillRepository skillRepository;
        @Autowired
        private final PositionRepository positionRepository;

        @Autowired
        private final JobPostingRepository jobPostingRepository;

        @Autowired
        PositionConverter positionConverter;

        public ResponseDTO deleteListPosition(PositionRequestDTO request) {

                try {
                        positionRepository.deleteAllById(request.getDeleteIds());
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        ResponseDTO.builder()
                                                        .message("Delete position")
                                                        .data("Position is deleted")
                                                        .build())
                                        .getBody();
                } catch (Exception ex) {

                        return ResponseEntity.status(HttpStatus.OK).body(
                                        ResponseDTO.builder()
                                                        .message("Failed delete position")
                                                        .data("Error: "+ex.getMessage())
                                                        .build())
                                        .getBody();
                }
        }

        @Override
        public ResponseEntity<ResponseObject> getAllPosition() {
                return ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .status("OK")
                                .message((String) Constant.UPDATE_PROFILE_SUCCESS)
                                .data(positionConverter.toDTO(positionRepository.findById(1L))).build());
        }
}
