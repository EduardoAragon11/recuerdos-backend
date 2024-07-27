package com.example.demo.photo.application;

import com.example.demo.photo.domain.PhotoService;
import com.example.demo.photo.dto.NewPhotoDTO;
import com.example.demo.photo.dto.PhotoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/photo")
@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePhoto(@PathVariable Long id){
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> patchPhoto(@PathVariable Long id, @RequestBody NewPhotoDTO newPhotoDTO){
        photoService.patchPhoto(id, newPhotoDTO);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getImage(@PathVariable Long id){
        byte[] image = photoService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
