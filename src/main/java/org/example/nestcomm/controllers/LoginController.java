package org.example.nestcomm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {this.userService = userService;}

    @GetMapping("/login")
    String login(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails != null) {
            log.info("password: {}", userDetails.getPassword());
            log.info("username: {}", userDetails.getUsername());
            model.addAttribute("currentUser", userDetails.getUser());
        }
        else {
            User user = new User();
            user.setEmail(null);
            user.setPassword(null);
            model.addAttribute("currentUser", user);
        }
        return "/login";
    }

    // Запоминает дату последнего успешного логина
    @GetMapping("/login-success")
    String successLogin(@AuthenticationPrincipal UserDetails userDetails) {
        userService.saveLoginDate(userDetails.getUser().getEmail(), LocalDateTime.now());
        return "redirect:/home";
    }

    @GetMapping("/login-error")
    String errorLogin(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String error = "login-error";
        User user = new User();
        user.setEmail(null);
        user.setPassword(null);
        model.addAttribute("currentUser", user);
        model.addAttribute("error", error);
        return "/login";
    }


}
