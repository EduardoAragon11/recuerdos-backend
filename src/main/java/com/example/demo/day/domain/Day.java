package com.example.demo.day.domain;

import com.example.demo.event.domain.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    LocalDate date;

    @OneToMany(mappedBy = "day")
    List<Event> events = new ArrayList<>();

    void addEvent(Event event){
        events.add(event);
    }
}
