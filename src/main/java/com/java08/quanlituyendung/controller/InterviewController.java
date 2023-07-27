package com.java08.quanlituyendung.controller;

import com.java08.quanlituyendung.dto.InterviewCreateDTO;
import com.java08.quanlituyendung.dto.InterviewDetailDTO;
import com.java08.quanlituyendung.dto.InterviewPayload.InterviewerToInterviewDTO;
import com.java08.quanlituyendung.dto.ResponseObject;
import com.java08.quanlituyendung.service.IInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor
public class InterviewController {
    private final IInterviewService service;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllInterviews() {
        return service.getAll();
    }
    @GetMapping("/{jobPostId}")
    public ResponseEntity<ResponseObject> getByJobPostId(@PathVariable Long jobPostId) {
        return service.getByJobPostId(jobPostId);
    }
    @GetMapping("/interviewers")
    public ResponseEntity<ResponseObject> getAllInterviewer() {
        return service.getAllInterviewer();
    }

    @GetMapping("/candidates/{jobPostId}")
    public ResponseEntity<ResponseObject> getAllCandidateByJd(@PathVariable Long jobPostId) {
        return service.getAllCandidateByJd(jobPostId);
    }

    @PostMapping("/create-interview")
    public ResponseEntity<ResponseObject> addInterview(@RequestBody InterviewCreateDTO interview) {
        return service.addInterview(interview);
    }
    @PostMapping("/interviewerAssign")
    public ResponseEntity<ResponseObject> addOneInterviewer(@RequestBody InterviewerToInterviewDTO interviewerToInterview) {
        return service.addOneInterviewer(interviewerToInterview);
    }
    @PostMapping("/candidateAssign")
    public ResponseEntity<ResponseObject> addDetailInterview(@RequestBody InterviewDetailDTO interviewDetailDTO) {
//        return service.addInterviewDetail(interviewDetailDTO);
        return ResponseEntity.ok(ResponseObject.builder().build());
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<?> getInterview(@PathVariable("id") Long id) {
//        InterviewCreateDTO dto = service.getInterviewById(id);
//        if (dto != null)
//            return new ResponseEntity<>(dto, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateInterview(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "false") Boolean overwrite, @RequestBody InterviewCreateDTO dto) {
//        InterviewCreateDTO updated = service.updateInterview(dto, id, overwrite);
//        if (updated != null)
//            return new ResponseEntity<>(updated, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @DeleteMapping
//    public void deleteInterview(@RequestBody List<Long> ids) {
//        for (Long id: ids) {
//            service.removeInterview(id);
//        }
//    }


}
