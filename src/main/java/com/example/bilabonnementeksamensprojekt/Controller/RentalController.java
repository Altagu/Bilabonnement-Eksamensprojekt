package com.example.bilabonnementeksamensprojekt.Controller;

import com.example.bilabonnementeksamensprojekt.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentalController {

    @Autowired
    private CarService carService;

    @GetMapping("/lejeaftale")
    public String lejeaftale(Model model) {
        model.addAttribute("cars", carService.fetchAll());
        return "home/lejeaftale";
    }
}
