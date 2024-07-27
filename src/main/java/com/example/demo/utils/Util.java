package com.example.demo.utils;

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
}

//https://medium.com/shoutloudz/spring-boot-upload-and-download-images-using-jpa-b1c9ef174dc0