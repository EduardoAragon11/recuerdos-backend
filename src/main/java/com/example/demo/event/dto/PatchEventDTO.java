package com.example.demo.event.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class PatchEventDTO {
    String name;
    public LocalTime time;
    Integer size;

}
