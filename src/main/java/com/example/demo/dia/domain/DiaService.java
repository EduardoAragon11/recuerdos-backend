package com.example.demo.dia.domain;

import com.example.demo.dia.dto.DiaResponseDTO;
import com.example.demo.dia.dto.NewDiaDTO;
import com.example.demo.dia.dto.SimpleDiaResponseDTO;
import com.example.demo.dia.infrastructure.DiaRepository;
import com.example.demo.event.domain.Event;
import com.example.demo.event.dto.EventSelfDto;
import com.example.demo.event.dto.NewEventDTO;
import com.example.demo.event.infrastructure.EventRepository;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.*;

import static java.util.Collections.sort;

@Service
public class DiaService {
    @Autowired
    private DiaRepository diaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventRepository eventRepository;

    public DiaResponseDTO getDay(Long id){
        Dia dia = diaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El dia no existe"));
        DiaResponseDTO diaResponseDTO = new DiaResponseDTO();
        List<EventSelfDto> events = new ArrayList<EventSelfDto>();

        dia.getEvents().forEach(event -> {events.add(modelMapper.map(event, EventSelfDto.class));});
        Collections.sort(events, new Comparator<EventSelfDto>() {
            public int compare(EventSelfDto o1, EventSelfDto o2) {
                return o1.time.compareTo(o2.time);
            }
        });

        diaResponseDTO.setId(dia.getId());
        diaResponseDTO.setEvents(events);
        diaResponseDTO.setDate(dia.getDate());

        return diaResponseDTO;
    }

    public void newDay(NewDiaDTO day){
        Optional<Dia> dia = diaRepository.findDiaByDate(day.getDate());
        if(dia.isPresent()) throw (new ResourceAlreadyExistsException("El dia ya existe"));
        diaRepository.save(modelMapper.map(day, Dia.class));
    }

    public void newEvent(Long id, NewEventDTO eventDto){
        Event event = modelMapper.map(eventDto,Event.class);
        Dia dia = diaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El dia no existe"));
        event.setDia(dia);
        event.setSize(50);
        dia.addEvent(event);

        eventRepository.save(event);
        diaRepository.save(dia);
    }

    public List<SimpleDiaResponseDTO> getDayByYearAndMonth(String year, String month){
        List<Dia> dias = diaRepository.findAllByYearAndMonth(year, month);
        List<SimpleDiaResponseDTO> simpleDias = new ArrayList<SimpleDiaResponseDTO>();
        dias.forEach(fecha -> {
            SimpleDiaResponseDTO simpleDiaResponseDTO = new SimpleDiaResponseDTO();
            simpleDiaResponseDTO.setId(fecha.getId());
            simpleDiaResponseDTO.setNumero_dia(fecha.getDate().getDayOfMonth());
            simpleDias.add(simpleDiaResponseDTO);
        });
        return simpleDias;
    }
    /*public void addEvent(Long idDay, Long idEvent){

    }*/
    public void deleteDay(Long id){
        diaRepository.deleteById(id);
    }

}
