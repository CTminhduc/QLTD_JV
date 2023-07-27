package com.java08.quanlituyendung.controller;

import com.google.api.services.calendar.model.Event;
import com.java08.quanlituyendung.calendar.CalendarService;
import com.java08.quanlituyendung.dto.*;
import com.java08.quanlituyendung.dto.EventPayload.LinkResponse;
import com.java08.quanlituyendung.service.IEventService;
import com.java08.quanlituyendung.service.IFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor

public class EventController {

    @Autowired
    IEventService eventService;

    @Autowired
    CalendarService calendarService;
    private final IFileUploadService fileUploadService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllEvent(Authentication authentication) {
        return eventService.getAllEvent(authentication);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getEvent(@PathVariable Long id, Authentication authentication) {
        return eventService.getEvent(id, authentication);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createEvent(@RequestBody CreateEventRequestDTO request, Authentication authentication) {
        return eventService.createEvent(request, authentication);
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateEvent(@RequestBody EventDTO request,@PathVariable long id, Authentication authentication){
        return eventService.updateEvent(request,id, authentication);
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> delete(@RequestParam String eventId, Authentication authentication) {
        return eventService.deleteEvent(eventId, authentication);
    }


    @GetMapping("/calendar")
    public LinkResponse getUrl() {
        try {
            return calendarService.getURL();
        }catch (Exception e){
            calendarService.stopLocalServerReceiver();
            return LinkResponse.builder().link("BUG").build();
        }
    }
    @PostMapping("/calendar")
    public Event calendar(@RequestBody CalendarAddRequestDTO requestDTO) {
        try {
            return calendarService.createEvent(requestDTO);
        }catch (Exception e){
            calendarService.stopLocalServerReceiver();
            return new Event();
        }

    }
    @PostMapping("/{id}/upload/image")
    public ResponseEntity<ResponseObject> uploadCV(@RequestParam("file") MultipartFile file,@PathVariable long id, Authentication authentication) {
        try {
            String fileUrl = fileUploadService.uploadFile(file);
            EventDTO eventDTO = new EventDTO();
            eventDTO.setImage(fileUrl);
            return eventService.updateEvent(eventDTO,id ,authentication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
