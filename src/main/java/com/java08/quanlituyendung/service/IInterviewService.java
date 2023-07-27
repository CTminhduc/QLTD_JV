package com.java08.quanlituyendung.service;

import com.java08.quanlituyendung.dto.InterviewCreateDTO;
import com.java08.quanlituyendung.dto.InterviewPayload.InterviewerToInterviewDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface IInterviewService {
    ResponseEntity<ResponseObject> getAll();

    ResponseEntity<ResponseObject> addInterview(InterviewCreateDTO interview);

    Boolean removeInterview(Long id);

    Boolean hasInterview(Long id);

    InterviewCreateDTO getInterviewById(Long id);

    InterviewCreateDTO updateInterview(InterviewCreateDTO interview, Long id, Boolean overwrite);

    // them 1 nguoi vao cuoc phong van
    ResponseEntity<ResponseObject> addOneInterviewer(InterviewerToInterviewDTO interviewerToInterview);

    ResponseEntity<ResponseObject> getByJobPostId(Long jobPostId);

    ResponseEntity<ResponseObject> getAllInterviewer();

    ResponseEntity<ResponseObject> getAllCandidateByJd(Long jobPostId);
}


