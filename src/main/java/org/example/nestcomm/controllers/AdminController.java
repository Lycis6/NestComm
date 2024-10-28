package org.example.nestcomm.controllers;

import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {
    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/ban/{email}")
    public String banUserWithEmail(@PathVariable String email, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        userService.banUserWithEmail(email);
        return "admin";
    }

    @GetMapping("/admin/unban/{email}")
    public String unbanUserWithEmail(@PathVariable String email, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        userService.unbanUserWithEmail(email);
        return "admin";
    }


}
