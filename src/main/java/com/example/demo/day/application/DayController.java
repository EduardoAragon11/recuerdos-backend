package com.example.demo.day.application;

import com.example.demo.day.domain.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/day")
public class DayController {
    @Autowired
    DayService dayService;

}
