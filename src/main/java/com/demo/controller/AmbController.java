package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmbController {

    @GetMapping("/amb")
    public String amb() {
        return "forward:/amb.html";
    }
}
