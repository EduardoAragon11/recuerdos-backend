package com.example.demo.event.application;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventService;
import com.example.demo.event.dto.EventResponseDTO;
import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.event.dto.PatchEventDTO;
import com.example.demo.photo.domain.PhotoService;
import com.example.demo.photo.dto.NewPhotoDTO;
import com.example.demo.photo.dto.PhotoResponseDTO;
import com.example.demo.photo.dto.PhotoResponseEditDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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


    @GetMapping("/simple/{id}")
    public ResponseEntity<PatchEventDTO> getSimpleEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getSimpleEvent(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchEvent(@PathVariable Long id, @RequestBody PatchEventDTO patchEventDTO){
        eventService.patchEvent(id, patchEventDTO);
        return ResponseEntity.ok().build();
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

    @PostMapping("/{id}/photos")
    public ResponseEntity<Void> uploadImages(@RequestParam("images")List<MultipartFile> files, @PathVariable Long id) throws IOException{
        eventService.uploadImages(id, files);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}/photos")
    public ResponseEntity<List<PhotoResponseEditDTO>> getImages(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getPhotosById(id));
    }

    @GetMapping("/{id}/choosen_photos")
    public ResponseEntity<List<PhotoResponseDTO>> getChoosenPhotos(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getChoosenPhotosById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
