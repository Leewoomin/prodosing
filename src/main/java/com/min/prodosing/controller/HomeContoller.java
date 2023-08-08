package com.min.prodosing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {

    @GetMapping("index")
    public String home() {
        return "index";
    }
}
