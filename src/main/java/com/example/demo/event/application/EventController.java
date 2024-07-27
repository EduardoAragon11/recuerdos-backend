package com.example.demo.event.application;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventService;
import com.example.demo.event.dto.EventResponseDTO;
import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.photo.domain.PhotoService;
import com.example.demo.photo.dto.NewPhotoDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/event")
@Controller
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    private PhotoService photoService;

    @PostMapping()
    public ResponseEntity<Void> newEvent(@RequestBody NewEventDTO newEventDTO){
        eventService.newEvent(newEventDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @PutMapping("/{id}/photo")
    public ResponseEntity<Void> addPhoto(@RequestBody NewPhotoDTO newPhotoDTO, @PathVariable Long id){
        eventService.addPhoto(id, newPhotoDTO);
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<Void> uploadImage(@RequestParam("image")MultipartFile file, @PathVariable Long id) throws IOException{
        eventService.uploadImage(id,file);
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
