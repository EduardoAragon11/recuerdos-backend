package com.example.demo.utils;

import com.example.demo.event.domain.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public String folderPathName(Event event, Long id){
        return "dia_" + event.getDia().getDate() +
                "/evento_" + event.getId();
    }
}

//https://medium.com/shoutloudz/spring-boot-upload-and-download-images-using-jpa-b1c9ef174dc0