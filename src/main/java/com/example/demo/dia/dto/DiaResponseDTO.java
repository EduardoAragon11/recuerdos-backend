package com.example.demo.dia.dto;

import com.example.demo.event.domain.Event;
import com.example.demo.event.dto.EventResponseDTO;
import com.example.demo.event.dto.EventSelfDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiaResponseDTO {
    Long id;

    LocalDate date;

    List<EventSelfDto> events;
}
