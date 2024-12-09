package com.example.bilabonnementeksamensprojekt.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "RentalContracts")
public class RentalContract {
    @Id
    private int contractID;
    private int customerID;
    private int carID;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "rentalFee")
    private BigDecimal rentalFee;
    @Column(name = "status")
    private String status = "Active"; // Default status is Active

    public RentalContract() {
    }

    public RentalContract(int contractID, int customerID, int carID, LocalDate startDate, LocalDate endDate, BigDecimal rentalFee, String status) {
        this.contractID = contractID;
        this.customerID = customerID;
        this.carID = carID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalFee = rentalFee;
        this.status = status;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(BigDecimal rentalFee) {
        this.rentalFee = rentalFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}