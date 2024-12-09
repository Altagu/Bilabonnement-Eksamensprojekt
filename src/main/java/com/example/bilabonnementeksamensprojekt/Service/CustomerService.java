package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.Customer;
import com.example.bilabonnementeksamensprojekt.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Create or update a customer
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // Fetch a specific customer by ID
    public Optional<Customer> getCustomer(int customerID) {
        return customerRepo.findById(customerID);
    }

    // Fetch all customers
    public List<Customer> fetchAllCustomers() {
        return customerRepo.findAll();
    }

    // Delete a customer
    public void deleteCustomer(int customerID) {
        customerRepo.deleteById(customerID);
    }

    // Update customer information
    public Customer updateCustomer(int customerID, Customer updatedCustomer) {
        return customerRepo.findById(customerID).map(customer -> {
            customer.setFname(updatedCustomer.getFname());
            customer.setLname(updatedCustomer.getLname());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setAddress(updatedCustomer.getAddress());
            return customerRepo.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerID));
    }
}