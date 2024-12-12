package com.example.bilabonnementeksamensprojekt.Model;

public class Car {
    private int id;
    private String vin;
    private String brand;
    private String model;
    private String fuelType;
    private double pricePrMonth;
    private enum Status {
        Udlejet,
        Tilbageleveret,
        Klar_til_udlejning,
        Skadet
    }

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
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getPricePrMonth() {
        return pricePrMonth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setPricePrMonth(double pricePrMonth) {
        this.pricePrMonth = pricePrMonth;
    }

}