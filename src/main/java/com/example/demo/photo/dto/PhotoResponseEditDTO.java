package com.example.demo.photo.dto;

import lombok.Data;

@Data
public class PhotoResponseEditDTO {
    Long id;
    Integer screenX;
    Integer screenY;
    Boolean choosen;
    Integer size;
    String url;
}
