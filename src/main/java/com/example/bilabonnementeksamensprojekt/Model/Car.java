package com.example.bilabonnementeksamensprojekt.Model;

public class Car {
    private int id;
    private String vin;  // Changed to lowercase
    private String brand;  // Changed to lowercase
    private String model;  // Changed to lowercase
    private String fuelType;  // Changed to camelCase
    private double pricePrMonth;  // Changed to camelCase

    public Car() {
    }

    public Car(int id, String vin, String brand, String model, String fuelType, double pricePrMonth) {
        this.id = id;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.pricePrMonth = pricePrMonth;
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

    public void setId(int id) {
        this.id = id;
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
}