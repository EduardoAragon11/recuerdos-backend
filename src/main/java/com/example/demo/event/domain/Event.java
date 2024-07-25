package com.example.demo.event.domain;

import com.example.demo.day.domain.Day;
import com.example.demo.photo.domain.Photo;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    String time;

    @Column
    String description;

    @Column
    Double latitude;

    @Column
    Double longitude;

    @ManyToOne
    Day day;

    @OneToMany(mappedBy = "event")
    List<Photo> photos = new ArrayList<>();

    void addPhoto(Photo photo){
        photos.add(photo);
    }
}
