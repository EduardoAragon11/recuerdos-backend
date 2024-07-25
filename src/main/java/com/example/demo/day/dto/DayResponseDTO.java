package com.example.demo.day.dto;

import com.example.demo.event.domain.Event;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DayResponseDTO {
    LocalDate date;

    List<Event> events;
}
