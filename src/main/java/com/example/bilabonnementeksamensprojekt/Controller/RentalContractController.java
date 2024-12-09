package com.example.bilabonnementeksamensprojekt.Controller;

import com.example.bilabonnementeksamensprojekt.Model.Customer;
import com.example.bilabonnementeksamensprojekt.Model.RentalContract;
import com.example.bilabonnementeksamensprojekt.Repo.CarRepo;
import com.example.bilabonnementeksamensprojekt.Repo.CustomerRepo;
import com.example.bilabonnementeksamensprojekt.Repo.RentalContractRepo;
import com.example.bilabonnementeksamensprojekt.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rentalContracts")
public class RentalContractController {

    private final CustomerRepo customerRepo;
    private final CarRepo carRepo;
    private final RentalContractRepo rentalContractRepo;
    private final CustomerService customerService;

    @Autowired
    public RentalContractController(CustomerRepo customerRepo, CarRepo carRepo, RentalContractRepo rentalContractRepo, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.carRepo = carRepo;
        this.rentalContractRepo = rentalContractRepo;
        this.customerService = customerService;
    }

    @GetMapping("/createRentalContract")
    public String showCreateForm(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("cars", carRepo.fetchAll());
        model.addAttribute("rentalContract", new RentalContract());
        return "home/rental/createRentalContract";
    }

    @PostMapping("/submitRentalContract")
    public String createRentalContract(@ModelAttribute RentalContract rentalContract) {
        System.out.println("Rental Contract: " + rentalContract);  // Log the received object
        rentalContractRepo.save(rentalContract);
        return "redirect:/rentalContracts/listRentalContracts";
    }

    @GetMapping("/listRentalContracts")
    public String listRentalContracts(Model model) {
        model.addAttribute("rentalContracts", rentalContractRepo.findAll());
        return "home/rental/listRentalContracts";  // Assuming you have a list view for rental contracts
    }
}