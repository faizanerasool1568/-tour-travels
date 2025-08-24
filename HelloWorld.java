package com.project.tour.travels.TourTravels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/test")
    String hello() {
        return "Hello World, Spring Boot!";
    }

}
