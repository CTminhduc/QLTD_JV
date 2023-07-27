package com.java08.quanlituyendung.converter;

import java.util.ArrayList;
import java.util.List;

import com.java08.quanlituyendung.dto.InterviewPayload.CandidateRoomItemDTO;
import com.java08.quanlituyendung.dto.InterviewPayload.RoomResponseDTO;
import com.java08.quanlituyendung.dto.UserAccountPayload.UserAccountCustomResponseDTO;
import com.java08.quanlituyendung.entity.InterviewDetailEntity;
import com.java08.quanlituyendung.entity.JobPostingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java08.quanlituyendung.dto.InterviewCreateDTO;
import com.java08.quanlituyendung.entity.InterviewEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InterviewConverter {
    @Autowired
    UserAccountConverter userAccountConverter;
    public RoomResponseDTO toDto(InterviewEntity interview) {
        RoomResponseDTO responseDTO = new RoomResponseDTO();
        responseDTO.setJobPostId(interview.getJobPostingEntity().getId());
        responseDTO.setId(interview.getId());
        responseDTO.setRoomName(interview.getRoomName());
        responseDTO.setRoomSkill(interview.getSkill());
        responseDTO.setRoomDescription(interview.getDescription());
        responseDTO.setStartDate(interview.getStartDate());
        responseDTO.setEndDate(interview.getEndDate());
        responseDTO.setStatus(interview.getStatus());

        List<UserAccountCustomResponseDTO> lists = new ArrayList<>();
        for (var i:interview.getInterviewers() ){
            lists.add(userAccountConverter.AccountToCustomeResponse(i));
        }
        responseDTO.setListInterviewer(lists);


        List<CandidateRoomItemDTO> listCandidate = new ArrayList<>();
        for (var j:interview.getInterviewDetailEntities()){
            listCandidate.add(InterviewDetailToCandidateItem(j));
        }
        responseDTO.setListCandidate(listCandidate);

        return responseDTO;
    }

    public InterviewEntity toEntity(InterviewCreateDTO dto, JobPostingEntity jobPostingEntity) {
        InterviewEntity interview = new InterviewEntity();
        interview.setJobPostingEntity(jobPostingEntity);
        interview.setRoomName(dto.getRoomName());
        interview.setSkill(dto.getRoomSkill());
        interview.setDescription(dto.getRoomDescription());
        interview.setStartDate(dto.getStartDate());
        interview.setEndDate(dto.getEndDate());
        interview.setStatus(dto.getStatus());
        interview.setInterviewDetailEntities(new ArrayList<>());
        return interview;
    }

    public CandidateRoomItemDTO InterviewDetailToCandidateItem(InterviewDetailEntity interviewDetail) {
        CandidateRoomItemDTO itemDTO = new CandidateRoomItemDTO();
        itemDTO.setCandidateId(interviewDetail.getCandidate().getId());
        itemDTO.setDate(itemDTO.getDate());
        itemDTO.setTime(itemDTO.getTime());
        return  itemDTO;
    }
}
