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
