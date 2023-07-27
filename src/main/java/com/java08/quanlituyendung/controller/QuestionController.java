package com.java08.quanlituyendung.controller;

import com.java08.quanlituyendung.dto.BasicResponseDTO;
import com.java08.quanlituyendung.dto.QuestionDTO;
import com.java08.quanlituyendung.dto.QuestionRequestDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.service.IQuestionService;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody QuestionRequestDTO request, Authentication authentication) {
        return questionService.createQuestion(request, authentication);
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> delete(@RequestBody Long[] ids, Authentication authentication) {

        return questionService.deleteQuestions(ids, authentication);
    }

    @PutMapping
    public ResponseEntity<ResponseObject> update(@RequestBody QuestionDTO request, Authentication authentication) {

        return questionService.updateQuestion(request, authentication);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllByPositionSkill(@RequestParam("pid") Long pid, @RequestParam("sid") Long sid) {

        return  questionService.getAllQuestionsByPositionSkill(pid,sid);
    }
}
