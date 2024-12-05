package com.example.bilabonnementeksamensprojekt.Controller;


import com.example.bilabonnementeksamensprojekt.Model.Car;
import com.example.bilabonnementeksamensprojekt.Model.DamageReport;
import com.example.bilabonnementeksamensprojekt.Model.DamageType;
import com.example.bilabonnementeksamensprojekt.Repo.DamageReportRepo;
import com.example.bilabonnementeksamensprojekt.Service.CarService;
import com.example.bilabonnementeksamensprojekt.Service.DamageReportService;
import com.example.bilabonnementeksamensprojekt.Service.DamageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DamageReportController {

    @Autowired
    private CarService carService;

    @Autowired
    private DamageTypeService damageTypeService;
    @Autowired
    private DamageReportService damageReportService;

    @GetMapping("/skade")
    public String skade(Model model) {
        model.addAttribute("cars", carService.fetchAll());
        model.addAttribute("damageTypes", damageTypeService.fetchAll());
        return "home/skade";
    }

    @GetMapping("/damageReportResult")
    public String showDamageReportResult(Model model) {
        Car car = carService.getCarDetails(); // Fetch car details from a service
        if (car != null) {
            model.addAttribute("car", car);
        } else {
            model.addAttribute("error", "Car details not found!");
            return "errorPage"; // Redirect to an error page if needed
        }
        return "home/damageReportResult";
    }


    @PostMapping("/submit-damage-report")

    public String submitDamageReport(@RequestParam int carID,
                                     @RequestParam List<Integer> damageTypeIds,
                                     Model model) {
        System.out.println("Received carID: " + carID);
        System.out.println("Received damageTypeIds: " + damageTypeIds);
        try {
            List<DamageType> selectedDamageTypes = new ArrayList<>();
            double totalPrice = 0.0;
            for (Integer damageTypeId : damageTypeIds) {
                DamageType damageType = damageTypeService.findById(damageTypeId);
                if (damageType != null) {
                    System.out.println("Fetched DamageType: " + damageType.getName()); // Log name to ensure it's set
                    selectedDamageTypes.add(damageType);
                    totalPrice += damageType.getCost();
                }
            }

            if (selectedDamageTypes.isEmpty()) {
                model.addAttribute("errorMessage", "No valid damage types selected.");
                return "errorPage";
            }

            int reportID = damageReportService.createDamageReport(carID, selectedDamageTypes, totalPrice);
            DamageReport report = damageReportService.getDamageReportWithDetails(reportID);

            model.addAttribute("report", report);
            model.addAttribute("car", carService.findCarByID(report.getCarID()));
            model.addAttribute("selectedDamageTypes", report.getDamageDetails());
            model.addAttribute("totalPrice", report.getTotalCost());

            return "home/damageReportResult";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An unexpected error occurred.");
            return "errorPage";
        }
    }
}