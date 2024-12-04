package com.example.bilabonnementeksamensprojekt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";  // Home page
    }

    @GetMapping("/lejeaftale")
    public String lejeaftale() {
        return "home/lejeaftale";
    }
}