package com.java08.quanlituyendung.service;

import com.java08.quanlituyendung.dto.SkillDTO;
import com.java08.quanlituyendung.dto.SkillRequestDTO;
import com.java08.quanlituyendung.dto.ResponseDTO;

public interface ISkillService {


     SkillDTO getSkillName(long positionId, long skillId);

}
