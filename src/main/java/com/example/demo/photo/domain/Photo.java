package com.example.demo.photo.domain;

import com.example.demo.event.domain.Event;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Integer screenX;

    @Column
    Integer screenY;

    @ManyToOne
    Event event;

    String path;
}
