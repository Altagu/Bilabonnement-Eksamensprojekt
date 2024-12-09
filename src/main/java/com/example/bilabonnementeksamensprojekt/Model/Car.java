package com.example.bilabonnementeksamensprojekt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    private int id;
    private String vin;  // Changed to lowercase
    private String brand;  // Changed to lowercase
    private String model;  // Changed to lowercase
    private String fuelType;  // Changed to camelCase
    private double pricePrMonth;  // Changed to camelCase
    private String status;

    public Car() {
    }

    public Car(int id, String vin, String brand, String model, String fuelType, double pricePrMonth, String status) {
        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.pricePrMonth = pricePrMonth;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getVin() {
        return vin;  // Adjusted
    }

    public String getBrand() {
        return brand;  // Adjusted
    }

    public String getModel() {
        return model;  // Adjusted
    }

    public String getFuelType() {
        return fuelType;  // Adjusted
    }

    public double getPricePrMonth() {
        return pricePrMonth;  // Adjusted
    }

    public String getStatus() {
        return status;
    }

    public void setId(int carID) {
        this.id = carID;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setBrand(String brand) {
        this.brand = brand;  // Adjusted
    }

    public void setModel(String model) {
        this.model = model;  // Adjusted
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;  // Adjusted
    }

    public void setPricePrMonth(double pricePrMonth) {
        this.pricePrMonth = pricePrMonth;  // Adjusted
    }

    public void setStatus(String status) {
        this.status = status;
    }
}