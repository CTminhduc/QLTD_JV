package com.java08.quanlituyendung.converter;

import com.java08.quanlituyendung.dto.PositionDTO;
import com.java08.quanlituyendung.entity.PositionEntity;
import com.java08.quanlituyendung.repository.JobPostingRepository;
import com.java08.quanlituyendung.repository.PositionRepository;
import com.java08.quanlituyendung.repository.QuestionRepository;
import com.java08.quanlituyendung.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PositionConverter {
    @Autowired
    SkillConverter skillConverter;

    @Autowired
    private final PositionRepository positionRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final SkillRepository skillRepository;

    @Autowired
    private final JobPostingRepository jobPostingRepository;


    public PositionEntity toEntityById(int ID) {
        PositionEntity entity = positionRepository.findById(ID);
        if (entity != null) {
            return entity;

        }
        return null;

    }

    public PositionDTO toDTO(PositionEntity positionEntity) {
        return PositionDTO.builder()
                .id(positionEntity.getId())
                .positionName(positionEntity.getPositionName())
                .skills(skillConverter.toSkillsDTOList(positionEntity.getSkillsEntities())).build();
    }

    public List<PositionDTO> toListPositionDTO(List<PositionEntity> positionEntityList) {
        List<PositionDTO> positionDTOList = new ArrayList<>();
        for (PositionEntity p : positionEntityList) {
            positionDTOList.add(toDTO(p));
        }
        return positionDTOList;
    }

}
