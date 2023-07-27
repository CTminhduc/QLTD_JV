package com.java08.quanlituyendung.converter;

import com.java08.quanlituyendung.dto.FieldDTO;
import com.java08.quanlituyendung.entity.FieldEnum;
import com.java08.quanlituyendung.entity.QuestionEntity;
import com.java08.quanlituyendung.entity.SkillEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FieldConverter {
    @Autowired
    QuestionConverter questionConverter;
    public FieldDTO toDTO(SkillEntity skillEntity, FieldEnum fieldEnum) {

        List<QuestionEntity> questionEntityList = new ArrayList<>();

        for (QuestionEntity q : skillEntity.getQuestions()) {
            if (q.getField().equals(fieldEnum)) {
                questionEntityList.add(q);
            }
        }
        return FieldDTO.builder()
                .id((long) (fieldEnum.ordinal() + 1))
                .fieldName(fieldEnum.name())
                .questions(questionConverter.toListQuestionDTO(questionEntityList))
                .build();

    }
    public List<FieldDTO> toFieldDTOList(SkillEntity skillEntity) {
        List<FieldDTO> fieldDTOList = new ArrayList<>();
        for (FieldEnum f : FieldEnum.values()) {
            fieldDTOList.add(toDTO(skillEntity, f));

        }
        return fieldDTOList;
    }
}
