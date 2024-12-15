package com.lesvp.myJourneyCompanion.controller;

import com.lesvp.myJourneyCompanion.model.Role;
import com.lesvp.myJourneyCompanion.model.User;
import com.lesvp.myJourneyCompanion.security.CustomUserDetails;
import com.lesvp.myJourneyCompanion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String allUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/profile")
    public String getUserByUuid(Authentication authentication, Model model) {

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UUID uuid = userDetails.getUserUUID();


            User user = userService.getUser(uuid);

            if (user != null) {
                model.addAttribute("user", user);
                return "profile";
            } else {
                return "user-not-found";
            }
        } else {
            return "error";
        }
    }

    @PostMapping("/create")
    public String createUser(String username, String email, String password) {
        if (username == null || email == null || password == null) {
            return "Les données sont invalides";
        }

        String hashedPassword = UserService.hashPassword(password);

        User user = new User(
                username,
                email,
                hashedPassword,
                new ArrayList<>(List.of(Role.USER))
        );

        userService.createUser(user);

        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUser(String uuid, String username, String email) {
        if (username == null || email == null || uuid == null) {
            return "Les données sont invalides";
        }

        userService.update(UUID.fromString(uuid), username, email);

        return "redirect:/users/profile";
    }

    @DeleteMapping("/delete")
    public String deleteUser(UUID uuid) {
        if (uuid == null) {
            return "Les données sont invalides";
        }

        userService.delete(uuid);

        return "redirect:/";
    }
}
