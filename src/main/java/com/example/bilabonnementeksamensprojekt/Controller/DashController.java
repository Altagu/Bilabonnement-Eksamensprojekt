package com.example.bilabonnementeksamensprojekt.Controller;

import com.example.bilabonnementeksamensprojekt.Model.Car;
import com.example.bilabonnementeksamensprojekt.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashController {

    @Autowired
    private CarService carService;

    @GetMapping("/dash")
    public String dash(@RequestParam(value = "query", required = false) String query,
                       @RequestParam(value = "sort", required = false) String sort,
                       @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
                       Model model) {
        model.addAttribute("carsRented", carService.carsRented());
        model.addAttribute("expectedIncome", carService.expectedIncome());
        model.addAttribute("awaitingRepair", carService.awaitingRepair());
        model.addAttribute("cars", carService.fetchAll());

        List<Car> cars;
        if (query != null && !query.isEmpty()) {
            cars = carService.searchCars(query);
        } else {
            cars = carService.fetchAll();
        }
        if (sort != null && !sort.isEmpty()) {
            cars.sort((c1, c2) -> {
                switch (sort) {
                    case "id":
                        return "asc".equalsIgnoreCase(order) ? Integer.compare(c1.getId(), c2.getId())
                                : Integer.compare(c2.getId(), c1.getId());
                    case "vin":
                        return "asc".equalsIgnoreCase(order) ? c1.getVin().compareToIgnoreCase(c2.getVin())
                                : c2.getVin().compareToIgnoreCase(c1.getVin());
                    case "brand":
                        return "asc".equalsIgnoreCase(order) ? c1.getBrand().compareToIgnoreCase(c2.getBrand())
                                : c2.getBrand().compareToIgnoreCase(c1.getBrand());
                    case "model":
                        return "asc".equalsIgnoreCase(order) ? c1.getModel().compareToIgnoreCase(c2.getModel())
                                : c2.getModel().compareToIgnoreCase(c1.getModel());
                    case "fuelType":
                        return "asc".equalsIgnoreCase(order) ? c1.getFuelType().compareToIgnoreCase(c2.getFuelType())
                                : c2.getFuelType().compareToIgnoreCase(c1.getFuelType());
                    case "pricePrMonth":
                        return "asc".equalsIgnoreCase(order) ? Double.compare(c1.getPricePrMonth(), c2.getPricePrMonth())
                                : Double.compare(c2.getPricePrMonth(), c1.getPricePrMonth());
                    default:
                        return 0;
                }
            });
        }

        model.addAttribute("cars", cars);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);

        return "home/dash";

    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable int id, Model model) {
        Car car = carService.findCarByID(id);
        model.addAttribute("car", car);
        return "home/details";
    }

    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car);
        return "redirect:/dash";
    }

}
