package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.Users;
import com.example.bilabonnementeksamensprojekt.Repo.UsersRepo;
import com.example.bilabonnementeksamensprojekt.Utility.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    public Users login(String username, String password) {
        Users user = usersRepo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user; // Return the Users object
        } else {
            return null; // Return null if credentials are invalid
        }
    }
}
