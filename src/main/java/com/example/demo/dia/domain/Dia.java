package com.example.demo.dia.domain;

import com.example.demo.event.domain.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    LocalDate date;

    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL)
    List<Event> events = new ArrayList<>();

    void addEvent(Event event){
        events.add(event);
    }

}
