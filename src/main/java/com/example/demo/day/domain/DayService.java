package com.example.demo.day.domain;

import com.example.demo.day.dto.DayResponseDTO;
import com.example.demo.day.dto.NewDayDTO;
import com.example.demo.day.infrastructure.DayRepository;
import com.example.demo.event.domain.Event;
import com.example.demo.event.dto.NewEventDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayService {
    @Autowired
    DayRepository dayRepository;
    @Autowired
    private ModelMapper modelMapper;

    public DayResponseDTO getDay(Long id){
        return modelMapper.map(dayRepository.findById(id).orElseThrow(), DayResponseDTO.class);
    }

    public void newDay(NewDayDTO day){
        dayRepository.save(modelMapper.map(day, Day.class));
    }

    public void newEvent(Long id, NewEventDTO eventDto){
        Event event = modelMapper.map(eventDto,Event.class);
        Day day = dayRepository.findById(id).orElseThrow();
        day.addEvent(event);
    }

}
