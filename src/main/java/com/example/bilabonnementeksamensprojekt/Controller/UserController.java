package com.example.bilabonnementeksamensprojekt.Controller;

import com.example.bilabonnementeksamensprojekt.Model.User;
import com.example.bilabonnementeksamensprojekt.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addUserToModel(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
    }

    @GetMapping("/login")
    public String login() {
        return "home/user/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        try {
            User user = userService.checkLogin(username, password);

            if (user == null) {
                model.addAttribute("error", "User does not exist or password is incorrect.");
                return "home/user/login";
            }

            int userId = user.getUserID();

            // Log the role to verify it's correct
            System.out.println("Logged in user role: " + user.getRole());

            if (user.getRole().equals("ADMIN")) {
                session.setAttribute("user", user);
                return "redirect:/dash";
            } else if (user.getRole().equals("EMPLOYEE")) {
                session.setAttribute("user", user);
                return "redirect:/dash";
            } else if (user.getRole().equals("USER")) {
                session.setAttribute("user", user);
                return "redirect:/";
            } else {
                model.addAttribute("error", "invalid");
                return "/";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "index";
        }
    }

    @GetMapping("/createLogin")
    public String createLogin(Model model) {
        model.addAttribute("user", new User());
        return "home/user/createLogin";
    }

    @PostMapping("/createLogin")
    public String postCreateLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false, defaultValue = "USER") String role
    ) throws SQLException {
        // Log inputs for debugging
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);
        System.out.println("Received role: " + role);

        // Validate required fields
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Missing username or password!");
            return "redirect:/error"; // Redirect to an error page
        }

        // Create a new user object with defaults
        User user = new User(username, password, role);

        // Save user
        userService.createUser(user);

        // Redirect to the homepage
        return "redirect:/";
    }

    @GetMapping("/adminDashboard")
    public String admin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("ADMIN")) {
            return "home/adminDashboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/employee")
    public String employee(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("EMPLOYEE")) {
            return "home/employee";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clears the session and logs the user out
        return "redirect:/"; // Redirects to the home page or login page
    }
}