package com.example.bilabonnementeksamensprojekt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bilabonnementeksamensprojekt.Model.Car;
import com.example.bilabonnementeksamensprojekt.Repo.CarRepo;
import com.example.bilabonnementeksamensprojekt.Service.CarService;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CarService CarService;

    @GetMapping("/")
    public String index(Model model) {
        List<Car> availableCars = CarService.findAvailableCars();
        model.addAttribute("cars", availableCars);

        return "home/index";
    }
}