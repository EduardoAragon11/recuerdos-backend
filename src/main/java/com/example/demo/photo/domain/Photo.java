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
    @Value("-1")
    Integer screenX;

    @Column
    @Value("-1")
    Integer screenY;

    @ManyToOne
    Event event;

    //@Column
    //String name;

    @Lob
    @Column
    byte[] imageData;
    //String path;
}
