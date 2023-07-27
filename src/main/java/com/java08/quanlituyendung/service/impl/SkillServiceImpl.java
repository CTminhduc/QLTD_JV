package com.java08.quanlituyendung.service.impl;

import com.java08.quanlituyendung.converter.PositionConverter;
import com.java08.quanlituyendung.converter.SkillConverter;
import com.java08.quanlituyendung.dto.ResponseDTO;
import com.java08.quanlituyendung.dto.SkillDTO;
import com.java08.quanlituyendung.dto.SkillRequestDTO;
import com.java08.quanlituyendung.entity.SkillEntity;
import com.java08.quanlituyendung.repository.SkillRepository;
import com.java08.quanlituyendung.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService {

    @Autowired
    private final SkillRepository skillRepository;

    private final PositionConverter positionConverter;

    private final SkillConverter skillConverter;
    @Override
    public SkillDTO getSkillName(long positionId, long skillId) {
        Optional<SkillEntity> skillName = null;
        SkillDTO skillDTO = new SkillDTO();
        try {
            skillName = skillRepository.getSkillNameByPositionAndSkills(positionId, skillId);
            skillDTO = skillConverter.toDTO(skillName.get());
            return skillDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
