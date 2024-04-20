package com.example.migrations.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
@Tag(name = "Main controller", description = "Basic endpoints for MVC")
public class MainController {
    @GetMapping("/")
    public String home(){
        return "JOME";
    }

}
