package com.example.demo.event.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class EventSelfDto {
    Long id;
    String name;
    LocalTime time;
    String description;

}
