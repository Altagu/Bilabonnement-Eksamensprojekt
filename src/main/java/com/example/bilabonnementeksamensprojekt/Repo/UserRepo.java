package com.example.bilabonnementeksamensprojekt.Repo;

import com.example.bilabonnementeksamensprojekt.Model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    @Value("${DATABASE_URL}")
    private String dbUrl;
    @Value("${DATABASE_USERNAME}")
    private String dbUsername;
    @Value("${DATABASE_PASSWORD}")
    private String dbPassword;

    @PostConstruct
    public void init() {
        System.out.println("DB URL: " + dbUrl);
        System.out.println("DB Username: " + dbUsername);
        System.out.println("DB Password: " + dbPassword);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to find a user by username
    public User findUser(String username) {
        String SQL = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
    }

    // Method to check login credentials
    public User checkLogin(String username, String password) {
        User user = findUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    // Method to create a new user
    public void createUser(User user) {
        String SQL = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, user.getUsername(), user.getPassword(), user.getRole());
    }

    // Method to retrieve all users
    public List<User> showAllUsers() {
        String SQL = "SELECT * FROM users";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(User.class));
    }
}