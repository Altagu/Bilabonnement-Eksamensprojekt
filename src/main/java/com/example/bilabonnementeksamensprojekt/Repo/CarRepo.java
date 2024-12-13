package com.example.bilabonnementeksamensprojekt.Repo;

import com.example.bilabonnementeksamensprojekt.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {
    @Autowired

    JdbcTemplate template;
    // Fetch all cars
    public List<Car> fetchAll(){
        // Ensure the column names match exactly with those in your table
        String sql = "SELECT CarID as id, VIN, Brand, Model, Fueltype, PricePrMonth, Status FROM Cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    // Add a new car
    public void addCar(Car c){
        // Fix the syntax issue with the SQL query
        String sql = "INSERT INTO Cars (VIN, Brand, Model, Fueltype, PricePrMonth) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, c.getVin(), c.getBrand(), c.getModel(), c.getFuelType(), c.getPricePrMonth());
    }

    // Find car by ID
    public Car findCarByID(int id){
        String sql = "SELECT CarID as id, VIN, Brand, Model, Fueltype, PricePrMonth FROM Cars WHERE CarID = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    // Delete car by ID
    public Boolean deleteCar(int id){
        String sql = "DELETE FROM Cars WHERE CarID = ?";
        return template.update(sql, id) > 0;
    }

    // Update car details
    public void updateCar(Car c){
        String sql = "UPDATE Cars SET VIN = ?, Brand = ?, Model = ?, Fueltype = ?, PricePrMonth = ? WHERE CarID = ?";
        template.update(sql, c.getVin(), c.getBrand(), c.getModel(), c.getFuelType(), c.getPricePrMonth(), c.getId());
    }

    public List<Car> findAvailableCars() {
        String sql = "SELECT CarID as id, VIN, Brand, Model, Fueltype, PricePrMonth, Status " +
                "FROM Cars WHERE Status = 'Klar til udlejning'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    // Update car status by ID
    public void updateCarStatus(int carID, String status) {
        String sql = "UPDATE Cars SET Status = ? WHERE CarID = ?";
        template.update(sql, status, carID);
    }

    public int carsRented(){
        String sql = "SELECT COUNT(*) FROM Cars WHERE Status = 'Udlejet'";
        return template.queryForObject(sql, Integer.class);
    }

    public double expectedIncome(){
        String sql = "SELECT SUM(PricePrMonth) FROM Cars WHERE Status = 'Udlejet'";
        return template.queryForObject(sql, Double.class);
    }

    public int awaitingRepair(){
        String sql = "SELECT COUNT(*) FROM Cars WHERE Status = 'Skadet'";
        return template.queryForObject(sql, Integer.class);
    }

    public List<Car> searchCars(String query) {
        String sql = "SELECT CarID AS id, VIN, Brand, Model, Fueltype, PricePrMonth FROM Cars " +
                "WHERE VIN LIKE ? OR Brand LIKE ? OR Model LIKE ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        String criteria = "%" + query + "%";  // To match any occurrence of the query
        return template.query(sql, new Object[]{criteria, criteria, criteria}, rowMapper);
    }
}