package com.java08.quanlituyendung.converter;

import com.java08.quanlituyendung.dto.SkillDTO;
import com.java08.quanlituyendung.entity.SkillEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SkillConverter {

    @Autowired
    FieldConverter fieldConverter;
    public SkillDTO toDTO(SkillEntity skillsEntity){
        return SkillDTO.builder()
                .id(skillsEntity.getId())
                .skillName(skillsEntity.getSkillName())
                .field(fieldConverter.toFieldDTOList(skillsEntity))
                .build();
    }

    public List<SkillDTO> toSkillsDTOList(List<SkillEntity> skillsEntityList){
        List<SkillDTO> skillsDTOList = new ArrayList<>();
        for(SkillEntity f : skillsEntityList){
            skillsDTOList.add(toDTO(f));
        }
        return skillsDTOList;
    }
}

