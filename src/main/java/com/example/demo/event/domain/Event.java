package com.example.demo.event.domain;

import com.example.demo.dia.domain.Dia;
import com.example.demo.photo.domain.Photo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    LocalTime time;

    @Column
    String description;
/*
    @Column
    Double latitude;

    @Column
    Double longitude;
*/
    @ManyToOne
    Dia dia;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    List<Photo> photos = new ArrayList<>();

    void addPhoto(Photo photo){
        photos.add(photo);
    }
}
