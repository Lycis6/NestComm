package org.example.nestcomm.controllers;

import org.example.nestcomm.models.User;
import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/NestComm")
    public String registration() {
        return "loginPage";
    }

    @PostMapping("/NestComm/validate")
    public String validateUserDetails(@RequestParam("login") String login, @RequestParam("password") String password) {
        if(userService.validateUserDetails(login, password))
            return "redirect:/product";
        else
            return "loginPage";
    }

    @GetMapping("/NestComm/reg")
    public String registrationUser() {
        return "reg";
    }

    @PostMapping("/NestComm/reg/newUser")
    public String registrationUser(User user) {

        userService.createUser(user);
        return "redirect:/loginPage";
    }



}
