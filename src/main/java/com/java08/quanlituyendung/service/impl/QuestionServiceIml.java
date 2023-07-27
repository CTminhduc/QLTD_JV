package com.java08.quanlituyendung.service.impl;

import com.java08.quanlituyendung.converter.PositionConverter;
import com.java08.quanlituyendung.converter.QuestionConverter;
import com.java08.quanlituyendung.dto.BasicResponseDTO;
import com.java08.quanlituyendung.dto.QuestionDTO;
import com.java08.quanlituyendung.dto.QuestionRequestDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.entity.PositionEntity;
import com.java08.quanlituyendung.entity.QuestionEntity;
import com.java08.quanlituyendung.entity.SkillEntity;
import com.java08.quanlituyendung.repository.PositionRepository;
import com.java08.quanlituyendung.repository.QuestionRepository;
import com.java08.quanlituyendung.repository.SkillRepository;
import com.java08.quanlituyendung.service.IQuestionService;
import com.java08.quanlituyendung.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceIml implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private QuestionConverter questionConverter;
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionConverter positionConverter;

    @Override
    public ResponseEntity<ResponseObject> createQuestion(QuestionRequestDTO request, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<SkillEntity> skillEntityOptional =
                    skillRepository.getSkillNameByPositionAndSkills(request.getPId(), request.getSId());
            if (skillEntityOptional.isPresent()) {
                SkillEntity skillEntity = skillEntityOptional.get();
                QuestionEntity question = QuestionEntity.builder()
                        .question(request.getQuestion())
                        .skill(skillEntity)
                        .createdBy(authentication.getName())
                        .createTime(LocalDateTime.now())
                        .build();
                List<QuestionEntity> questionEntityList = skillEntity.getQuestions();
                questionEntityList.add(question);
                skillEntity.setQuestions(questionEntityList);
                skillRepository.save(skillEntity);

                return ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .message(Constant.SUCCESS)
                                .build());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseObject.builder()
                            .message(Constant.SKILL_NOT_FOUND)
                            .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ResponseObject.builder()
                            .message(Constant.NOT_AUTHENTICATED)
                            .build());

    }


    @Override
    public ResponseEntity<ResponseObject> updateQuestion(QuestionDTO questionDTO,Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            Optional<QuestionEntity> questionEntityOptional =
                    questionRepository.findOneById(questionDTO.getId());
            if(questionEntityOptional.isPresent()){
                QuestionEntity questionEntity = questionEntityOptional.get();
                questionEntity.setQuestion(questionDTO.getQuestion());
                questionRepository.save(questionEntity);
                return ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .message(Constant.SUCCESS)
                                .build());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseObject.builder()
                            .message(Constant.FAIL)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseObject.builder()
                        .message(Constant.NOT_AUTHENTICATED)
                        .build());

    }

    @Override
    public ResponseEntity<ResponseObject> deleteQuestions(Long[] qIds, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            List<QuestionEntity> questionEntities = questionRepository.findAllById(Arrays.asList(qIds));
            if (!questionEntities.isEmpty()) {
                questionEntities.forEach(questionEntity -> questionEntity.setIsDeleted(true));
                questionRepository.saveAll(questionEntities);
                return ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .message(Constant.SUCCESS)
                                .build());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseObject.builder()
                            .message(Constant.FAIL)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseObject.builder()
                        .message(Constant.NOT_AUTHENTICATED)
                        .build());
    }

    @Override
    public ResponseEntity<ResponseObject> getAllQuestionsByPositionSkill(Long pid, Long sid) {
        Optional<PositionEntity> positionOptional = positionRepository.findById(pid);
        if(positionOptional.isPresent()){
            PositionEntity positionEntity = positionOptional.get();
            Optional<SkillEntity> skillOptional = skillRepository.getSkillNameByPositionAndSkills(pid,sid);
            if(skillOptional.isPresent()){
                positionEntity.setSkillsEntities(skillOptional.stream().toList());
                return ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .message(Constant.SUCCESS)
                                .data(positionConverter.toDTO((positionEntity)))
                                .build());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseObject.builder()
                            .message("Not found SkillEntity")
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseObject.builder()
                        .message("Not found PositionEntity")
                        .build());
    }


}
