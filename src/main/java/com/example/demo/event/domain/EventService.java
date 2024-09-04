package com.example.demo.event.domain;

import com.example.demo.event.dto.EventResponseDTO;
import com.example.demo.event.dto.EventSelfDto;
import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.event.dto.PatchEventDTO;
import com.example.demo.event.infrastructure.EventRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.photo.domain.Photo;
import com.example.demo.photo.domain.PhotoService;
import com.example.demo.photo.dto.NewPhotoDTO;
import com.example.demo.photo.dto.PhotoResponseDTO;
import com.example.demo.photo.dto.PhotoResponseEditDTO;
import com.example.demo.photo.infrastructure.PhotoRepository;
import com.example.demo.utils.CloudinaryService;
import com.example.demo.utils.ImageUtil;
import com.example.demo.utils.Util;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private Util util;

    public EventResponseDTO getEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El evento no existe"));
        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        List<PhotoResponseDTO> photos_ = new ArrayList<>();
        event.getPhotos().forEach(photo -> {photos_.add(modelMapper.map(photo, PhotoResponseDTO.class));});

        eventResponseDTO.setId(event.getId());
        eventResponseDTO.setPhotos(photos_);
        return eventResponseDTO;
    }

    public void newEvent(NewEventDTO newEventDTO){
        Event event = modelMapper.map(newEventDTO, Event.class);
        event.setSize(50);
        eventRepository.save(event);
    }

    public void deleteEvent(Long idEvent){
        eventRepository.deleteById(idEvent);
    }


    public void addPhoto(Long idEvent, NewPhotoDTO newPhotoDTO){
        Photo photo = modelMapper.map(newPhotoDTO, Photo.class);
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        photo.setEvent(event);
        photo.setChoosen(false);
        photo.setSize(64);
        event.addPhoto(photo);

        photoRepository.save(photo);
        eventRepository.save(event);
    }


    public void uploadImage(Long idEvent, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        photo.setEvent(event);
        photo.setChoosen(false);

        photo = photoRepository.save(photo);

        photo.setUrl(cloudinaryService.uploadFile(file, util.folderPathName(event, photo.getId())));
        event.addPhoto(photo);

        photoRepository.save(photo);
        eventRepository.save(event);
    }


    public void uploadImages(Long idEvent, List<MultipartFile> files) throws IOException {
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        files.forEach(file -> {
            Photo photo = new Photo();
            photo.setChoosen(false);
            photo.setSize(64);
            photo.setEvent(event);

            photo = photoRepository.save(photo);

            photo.setUrl(cloudinaryService.uploadFile(file, util.folderPathName(event, photo.getId())));
            event.addPhoto(photo);
            photoRepository.save(photo);
        });
        eventRepository.save(event);
    }

    public PatchEventDTO getSimpleEvent(Long idEvent){
        return modelMapper.map(eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe")), PatchEventDTO.class);
    }

    public void patchEvent(Long idEvent, PatchEventDTO eventSelfDto){
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        if(eventSelfDto.getName() != null){
            event.setName(eventSelfDto.getName());
        }
        if(eventSelfDto.getTime() != null){
            event.setTime(eventSelfDto.getTime());
        }
        if(eventSelfDto.getSize() != null){
            event.setSize(eventSelfDto.getSize());
        }
        eventRepository.save(event);
    }


    public List<PhotoResponseEditDTO> getPhotosById (Long idEvent){
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        List<PhotoResponseEditDTO> photos = new ArrayList<PhotoResponseEditDTO>();
        event.photos.forEach(photo -> {
            PhotoResponseEditDTO photoResponseEditDTO = new PhotoResponseEditDTO();
            photoResponseEditDTO.setId(photo.getId());
            photoResponseEditDTO.setScreenX(photo.getScreenX());
            photoResponseEditDTO.setScreenY(photo.getScreenY());
            photoResponseEditDTO.setUrl(photo.getUrl());
            photoResponseEditDTO.setChoosen(photo.getChoosen());
            photoResponseEditDTO.setSize(photo.getSize());
            photos.add(photoResponseEditDTO);
        });
        photos.sort(Comparator.comparingLong(PhotoResponseEditDTO::getId));
        return photos;
    }

/*
    public void addPhotos(Long id, List<Photo> photos){
        Event event = eventRepository.findById(id).orElseThrow();
        for(Photo photo:photos){
            event.addPhoto(photo);
        }
    }
*/

    public List<PhotoResponseDTO> getChoosenPhotosById(Long idEvent){
        Event event = eventRepository.findById(idEvent).orElseThrow(()-> new ResourceNotFoundException("El evento no existe"));
        List<PhotoResponseDTO> photos = new ArrayList<PhotoResponseDTO>();
        event.photos.forEach(photo -> {
            if(photo.getChoosen()){
                PhotoResponseDTO photoResponseDTO = new PhotoResponseDTO();
                photoResponseDTO.setId(photo.getId());
                photoResponseDTO.setScreenX(photo.getScreenX());
                photoResponseDTO.setScreenY(photo.getScreenY());
                photoResponseDTO.setUrl(photo.getUrl());
                photoResponseDTO.setSize(photo.getSize());
                photos.add(photoResponseDTO);
            }
        });
        photos.sort(Comparator.comparingLong(PhotoResponseDTO::getId));
        return photos;
    }

}
