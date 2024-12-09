package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.RentalContract;
import com.example.bilabonnementeksamensprojekt.Repo.RentalContractRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalContractService {

    private final RentalContractRepo rentalContractRepo;

    public RentalContractService(RentalContractRepo rentalContractRepo) {
        this.rentalContractRepo = rentalContractRepo;
    }

    // Create or update a rental contract
    public RentalContract createRentalContract(RentalContract rentalContract) {
        return rentalContractRepo.save(rentalContract);
    }

    // Fetch a specific rental contract by ID
    public Optional<RentalContract> getRentalContract(int contractID) {
        return rentalContractRepo.findById(contractID);
    }

    // Fetch all rental contracts
    public List<RentalContract> fetchAllContracts() {
        return rentalContractRepo.findAll();
    }

    // Update the status of a rental contract
    public void updateContractStatus(int contractID, String newStatus) {
        Optional<RentalContract> rentalContractOpt = rentalContractRepo.findById(contractID);
        rentalContractOpt.ifPresent(rentalContract -> {
            rentalContract.setStatus(newStatus);
            rentalContractRepo.save(rentalContract);
        });
    }
}