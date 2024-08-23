package com.example.demo.photo.dto;

import lombok.Data;

@Data
public class PhotoEditDTO {
    Long id;
    Integer screenX;
    Integer screenY;
    Boolean choosen;
    Integer size;
}
