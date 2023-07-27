package com.java08.quanlituyendung.service.impl;

import com.java08.quanlituyendung.converter.InterviewConverter;
import com.java08.quanlituyendung.dto.InterviewCreateDTO;
import com.java08.quanlituyendung.dto.InterviewPayload.InterviewerToInterviewDTO;
import com.java08.quanlituyendung.dto.InterviewPayload.RoomResponseDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.entity.*;
import com.java08.quanlituyendung.repository.InterviewRepository;
import com.java08.quanlituyendung.repository.JobPostingRepository;
import com.java08.quanlituyendung.repository.UserAccountRepository;
import com.java08.quanlituyendung.service.IInterviewService;
import com.java08.quanlituyendung.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements IInterviewService {
    private InterviewRepository interviewRepository;
    private InterviewConverter converter;
    private UserAccountRepository userAccountRepository;
    private JobPostingRepository jobPostingRepository;

    @Autowired
    public InterviewServiceImpl(InterviewRepository interviewRepository, InterviewConverter converter, UserAccountRepository userAccountRepository, JobPostingRepository jobPostingRepository) {
        this.interviewRepository = interviewRepository;
        this.converter = converter;
        this.userAccountRepository = userAccountRepository;
        this.jobPostingRepository = jobPostingRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> getAll() {
        var listInterview =  interviewRepository.findAll();
        List<RoomResponseDTO> data = new ArrayList<>();
        for (var i: listInterview){
            var item = converter.toDto(i);
            data.add(item);
        }
        return ResponseEntity.ok(ResponseObject.builder()
                        .status("OK")
                        .message(Constant.SUCCESS)
                        .data(data)
                .build());
    }

    @Override
    public ResponseEntity<ResponseObject> getByJobPostId(Long jobPostId) {
        var listInterview =  interviewRepository.findByJobPostingEntityId(jobPostId);
        List<RoomResponseDTO> data = new ArrayList<>();
        for (var i: listInterview){
            var item = converter.toDto(i);
            data.add(item);
        }
        return ResponseEntity.ok(ResponseObject.builder()
                .status("OK")
                .message(Constant.SUCCESS)
                .data(data)
                .build());
    }

    @Override
    public ResponseEntity<ResponseObject> getAllInterviewer() {
        var list = userAccountRepository.findByRole(Role.INTERVIEWER);
        return ResponseEntity.ok(ResponseObject.builder()
                .data(list)
                .message("SUCCESS!")
                .build());
    }

    @Override
    public ResponseEntity<ResponseObject> getAllCandidateByJd(Long jobPostId) {
        Optional<JobPostingEntity> jd = jobPostingRepository.findById(jobPostId);
        if(jd.isPresent()) {
            var listcv = jd.get().getCvEntities();
            List<UserAccountEntity> listCandidate = listcv.stream()
                    .map(CVEntity::getUserAccountEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ResponseObject.builder()
                            .data(listCandidate)
                    .message("Success!")
                    .build());
        }else {
            return ResponseEntity.ok(ResponseObject.builder()
                    .message("Cannot find JobPostId!")
                    .build());
        }
    }


    /// tao interview|| tao room
    @Override
    public ResponseEntity<ResponseObject> addInterview(InterviewCreateDTO interview) {
        Optional<JobPostingEntity> jobPostingEntity = jobPostingRepository.findById(Long.parseLong(interview.getJobPostId()));
        if(jobPostingEntity.isPresent()){
            return ResponseEntity.ok(ResponseObject.builder()
                    .message("Success!")
                    .data(converter.toDto(interviewRepository.save(converter.toEntity(interview, jobPostingEntity.get()))))
                    .build());
        }
        return ResponseEntity.ok(ResponseObject.builder()
                .message("Cannot find JobPostId!")
                .build());
    }


    // them 1 nguoi phong van
    @Override
    public ResponseEntity<ResponseObject> addOneInterviewer(InterviewerToInterviewDTO request) {
        Optional<UserAccountEntity> interviewer = userAccountRepository.findByEmail(request.getInterviewerEmail());
        Optional<InterviewEntity> room = interviewRepository.findById(Long.parseLong(request.getRoomId()));
        if (interviewer.isPresent() && room.isPresent()) {
            var room1 = room.get();
            if (interviewer.get().getRole() != Role.INTERVIEWER || room1.getInterviewers().contains(interviewer.get())) {
                return ResponseEntity.ok(ResponseObject.builder()
                        .status("not success")
                        .message("This is not a interviewer or this interviewer is added")
                        .data(converter.toDto(room1))
                        .build());
            } else {
                var roomInterviewers = room1.getInterviewers();
                roomInterviewers.add(interviewer.get());
                room1.setInterviewers(roomInterviewers);
                return ResponseEntity.ok(ResponseObject.builder()
                        .status("success")
                        .message("Add sucess!")
                        .data(converter.toDto(interviewRepository.save(room1)))
                        .build());
            }
        } else {
            return ResponseEntity.ok(ResponseObject.builder()
                    .status("not success")
                    .message("Can't not find INTERVIEWER or ROOM")
                    .build());
        }
    }

    @Override
    public Boolean removeInterview(Long id) {
        if (hasInterview(id)) {
            interviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean hasInterview(Long id) {
        return interviewRepository.existsById(id);
    }

    @Override
    public InterviewCreateDTO getInterviewById(Long id) {
        return null;

    }

    public InterviewCreateDTO updateInterview(InterviewCreateDTO dto, Long id, Boolean overwrite) {
        return null;
    }
}



