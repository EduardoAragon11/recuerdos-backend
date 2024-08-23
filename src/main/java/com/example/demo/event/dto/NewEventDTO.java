package com.example.demo.event.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class NewEventDTO {
    String name;
    LocalTime time;

}
