package com.example.demo.photo.domain;

import com.example.demo.event.domain.Event;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Integer screenX;

    @Column
    Integer screenY;

    @Column
    @Value("false")
    Boolean choosen;

    @Column
    Integer size;

    @ManyToOne
    Event event;

    @Column
    String url;

}
