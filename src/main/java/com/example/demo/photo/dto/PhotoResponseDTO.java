package com.example.demo.photo.dto;

import lombok.Data;

@Data
public class PhotoResponseDTO {
    Long id;
    Integer screenX;
    Integer screenY;
    Integer size;
    String url;
}
