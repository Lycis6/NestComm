package org.example.nestcomm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
class LoginController {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/login-error")
    String errorLogin(Model model) {
        String error = "login-error";
        model.addAttribute("error", error);
        return "login";
    }


}
