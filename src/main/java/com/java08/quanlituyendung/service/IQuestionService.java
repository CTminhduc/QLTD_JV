package com.java08.quanlituyendung.service;

import com.java08.quanlituyendung.dto.QuestionDTO;
import com.java08.quanlituyendung.dto.QuestionRequestDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface IQuestionService {

    ResponseEntity<ResponseObject> createQuestion(QuestionRequestDTO questionRequestDTO, Authentication authentication);
    ResponseEntity<ResponseObject> updateQuestion(QuestionDTO questionDTO,Authentication authentication);
    ResponseEntity<ResponseObject> deleteQuestions(Long[] id,Authentication authentication);

    ResponseEntity<ResponseObject> getAllQuestionsByPositionSkill(Long pid, Long sid);

}
