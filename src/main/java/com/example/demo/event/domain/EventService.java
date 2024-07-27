package com.example.demo.event.domain;

import com.example.demo.event.dto.EventResponseDTO;
import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.event.infrastructure.EventRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.photo.domain.Photo;
import com.example.demo.photo.dto.NewPhotoDTO;
import com.example.demo.photo.dto.PhotoResponseDTO;
import com.example.demo.photo.infrastructure.PhotoRepository;
import com.example.demo.utils.ImageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PhotoRepository photoRepository;

    public EventResponseDTO getEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        List<PhotoResponseDTO> photos_ = new ArrayList<>();
        event.getPhotos().forEach(photo -> {photos_.add(modelMapper.map(photo, PhotoResponseDTO.class));});

        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setPhotos(photos_);
        return eventResponseDTO;
    }

    public void newEvent(NewEventDTO event){
        eventRepository.save(modelMapper.map(event, Event.class));
    }

    public void deleteEvent(Long idEvent){
        eventRepository.deleteById(idEvent);
    }


    public void addPhoto(Long idEvent, NewPhotoDTO newPhotoDTO){
        Photo photo = modelMapper.map(newPhotoDTO, Photo.class);
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        photo.setEvent(event);
        event.addPhoto(photo);

        photoRepository.save(photo);
        eventRepository.save(event);
    }


    public void uploadImage(Long idEvent, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setImageData(ImageUtil.compressImage(file.getBytes()));
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        photo.setEvent(event);
        event.addPhoto(photo);

        photoRepository.save(photo);
        eventRepository.save(event);
    }
/*
    public void addPhotos(Long id, List<Photo> photos){
        Event event = eventRepository.findById(id).orElseThrow();
        for(Photo photo:photos){
            event.addPhoto(photo);
        }
    }
*/

}
