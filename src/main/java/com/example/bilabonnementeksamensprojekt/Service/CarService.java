package com.example.bilabonnementeksamensprojekt.Service;


import com.example.bilabonnementeksamensprojekt.Model.Car;
import com.example.bilabonnementeksamensprojekt.Repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;

    public List<Car> fetchAll(){
        return carRepo.fetchAll();
    }

    public Car getCarDetails() {
        Car car = carRepo.findCarByID(1); // Assume this fetches the car object
        if (car == null) {
            try {
                throw new IOException("Car not found!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return car;
    }

     public void addCar(Car c){
        carRepo.addCar(c);
     }

     public Car findCarByID(int id){
        return carRepo.findCarByID(id);
     }

     public Boolean deleteCar(int id){
        return carRepo.deleteCar(id);
     }

     public void updateCar(Car c){
        carRepo.updateCar(c);
     }

     public int carsRented(){
        return carRepo.carsRented();
     }

     public double expectedIncome(){
        return carRepo.expectedIncome();
     }

     public int awaitingRepair(){
        return carRepo.awaitingRepair();
     }
    public List<Car> searchCars(String query) {
        return carRepo.searchCars(query);
    }

    public List<Car> findAvailableCars() {
        return carRepo.findAvailableCars();
    }
}
