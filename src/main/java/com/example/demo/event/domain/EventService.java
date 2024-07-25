package com.example.demo.event.domain;

import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.event.infrastructure.EventRepository;
import com.example.demo.photo.domain.Photo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    public Event getEvent(Long id){
        return eventRepository.findById(id).orElseThrow();
    }

    public void newEvent(NewEventDTO event){
        eventRepository.save(modelMapper.map(event, Event.class));
    }

    public void addPhoto(Long id, Photo photo){
        Event event = eventRepository.findById(id).orElseThrow();
        event.addPhoto(photo);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
