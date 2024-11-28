package com.example.bilabonnementeksamensprojekt.Controller;

import com.example.bilabonnementeksamensprojekt.Model.Users;
import com.example.bilabonnementeksamensprojekt.Service.UsersService;
import com.example.bilabonnementeksamensprojekt.Utility.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        Users user = usersService.login(username, password);
        ModelAndView modelAndView = new ModelAndView();

        if (user != null) {
            // Successful login, redirect based on user type
            if (user.getUserType() == UserType.ADMIN) {
                modelAndView.setViewName("redirect:/admin-dashboard");  // Redirect to admin dashboard
            } else if (user.getUserType() == UserType.DATA_USER) {
                modelAndView.setViewName("redirect:/data-dashboard");  // Redirect to data user dashboard
            } else {
                modelAndView.setViewName("redirect:/user-home");  // Redirect to general user home page
            }
        } else {
            // Invalid login, send back to login page with an error
            modelAndView.setViewName("redirect:/login?error=true");
        }

        return modelAndView;
    }
}
