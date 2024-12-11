package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.User;
import com.example.bilabonnementeksamensprojekt.Repo.UserRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User checkLogin(String username, String password) throws SQLException {
        return userRepo.checkLogin(username, password);
    }

    public void createUser(User user) throws SQLException {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        userRepo.createUser(user);
    }


    public List<User> showAllUsers() throws SQLException {
        return userRepo.showAllUsers();
    }
}