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
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("cars", carRepo.findAvailableCars());
        model.addAttribute("rentalContract", new RentalContract());
        return "home/rental/createRentalContract";
    }

    @PostMapping("/submitRentalContract")
    public String createRentalContract(@ModelAttribute RentalContract rentalContract,
                                       @RequestParam(name = "customerOption") String customerOption,
                                       @RequestParam(name = "customerID", required = false) Integer customerID,
                                       @RequestParam(name = "fname", required = false) String fname,
                                       @RequestParam(name = "lname", required = false) String lname,
                                       @RequestParam(name = "email", required = false) String email,
                                       @RequestParam(name = "phone", required = false) String phone,
                                       @RequestParam(name = "address", required = false) String address) {
        if ("new".equals(customerOption)) {
            // Create a new Customer
            Customer customer = new Customer();
            customer.setFname(fname);
            customer.setLname(lname);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);

            // Save the new Customer and get the generated ID
            customer = customerRepo.save(customer);

            // Assign the new customer's ID to the rental contract
            rentalContract.setCustomerID(customer.getCustomerID());
        } else if ("existing".equals(customerOption) && customerID != null) {
            // Use an existing customer ID
            rentalContract.setCustomerID(customerID);
        } else {
            throw new IllegalArgumentException("Invalid customer selection");
        }

        // Update car status to 'Udlejet'
        carRepo.updateCarStatus(rentalContract.getCarID(), "Udlejet");

        // Save the rental contract
        rentalContractRepo.save(rentalContract);

        return "redirect:/rentalContracts/listRentalContracts";
    }

    @GetMapping("/listRentalContracts")
    public String listRentalContracts(Model model) {
        model.addAttribute("rentalContracts", rentalContractRepo.findAll());
        return "home/rental/listRentalContracts";  // Assuming you have a list view for rental contracts
    }
}