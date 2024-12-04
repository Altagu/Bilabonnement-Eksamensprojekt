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
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        try {
            User user = userService.checkLogin(username, password);

            if (user == null) {
                model.addAttribute("error", "User does not exist or password is incorrect.");
                return "home/login";
            }

            int userId = user.getUserID();

            // Log the role to verify it's correct
            System.out.println("Logged in user role: " + user.getRole());

            if (user.getRole().equals("ADMIN")) {
                session.setAttribute("user", user);
                return "redirect:/";
            } else if (user.getRole().equals("EMPLOYEE")) {
                session.setAttribute("user", user);
                return "redirect:/employee/" + userId;
            } else if (user.getRole().equals("USER")) {
                session.setAttribute("user", user);
                return "redirect:/";
            } else {
                model.addAttribute("error", "invalid");
                return "index";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "index";
        }
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
        if (user != null && user.getRole().equals("employee")) {
            return "employee";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/projectManager")
    public String projectManager(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("pjManager")) {
            model.addAttribute("user", user);
            return "projectManager";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/createUser")
    public String createUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && "admin".equals(user.getRole())) {
            User defaultUser = new User();
            model.addAttribute("user", defaultUser);
            return "createUser";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/createUser")
    public String postCreateUser(@ModelAttribute User user, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && "admin".equals(currentUser.getRole())) {
            userService.createUser(user);
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/showUsers")
    public String showUsers(Model model, HttpSession session) throws SQLException {
        User user = (User) session.getAttribute("user");
        if (user != null && "admin".equals(user.getRole())) {
            List<User> userList = userService.showAllUsers();
            model.addAttribute("userList", userList);
            return "showUsers";
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