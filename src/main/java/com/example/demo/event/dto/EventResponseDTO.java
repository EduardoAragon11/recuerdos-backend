package com.example.demo.event.dto;

import com.example.demo.photo.domain.Photo;
import com.example.demo.photo.dto.PhotoResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class EventResponseDTO {
    Long id;
    List<PhotoResponseDTO> photos;
}
