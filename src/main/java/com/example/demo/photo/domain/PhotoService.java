package com.example.demo.photo.domain;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.photo.dto.NewPhotoDTO;
import com.example.demo.photo.dto.PhotoEditDTO;
import com.example.demo.photo.dto.PhotoResponseDTO;
import com.example.demo.photo.infrastructure.PhotoRepository;
import com.example.demo.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public void deletePhoto(Long id){
        photoRepository.deleteById(id);
    }

    public void patchPhoto(Long id, NewPhotoDTO newPhotoDTO){
        Photo photo = photoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Photo no encontrada"));
        photo.setScreenX(newPhotoDTO.getScreenX());
        photo.setScreenY(newPhotoDTO.getScreenY());
        photoRepository.save(photo);
    }


    public byte[] getImage(Long id){
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhotoData no encontrada"));
        return ImageUtil.decompressImage(photo.getImageData());
    }

    public void choosePhoto(Long id){
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Photo no encontrada"));
        photo.setChoosen(true);
        photoRepository.save(photo);
    }

    public void notChoosePhoto(Long id){
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Photo no encontrada"));
        photo.setChoosen(false);
        photoRepository.save(photo);
    }

    public void editPhotos(List<PhotoEditDTO> photos){
        photos.forEach(photo -> {
            Photo photoEdit = photoRepository.findById(photo.getId()).orElseThrow(() -> new ResourceNotFoundException("Photo no encontrada"));
            photoEdit.setChoosen(photo.getChoosen());
            if(!photo.getChoosen()){
                photoEdit.setScreenX(null);
                photoEdit.setScreenY(null);
                photoEdit.setSize(null);
            }
            else{
                photoEdit.setScreenX(photo.getScreenX());
                photoEdit.setScreenY(photo.getScreenY());
                photoEdit.setSize(photo.getSize());
            }
            photoRepository.save(photoEdit);
        });
    }

}
