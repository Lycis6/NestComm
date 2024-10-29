package org.example.nestcomm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/registration")
    public String registrationUser() {
        return "registration";
    }

    @PostMapping("/user/registration/new")
    public String registrationUser(User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if(userService.createUser(user))
            return "redirect:/login";
        return "registration";
    }

    @GetMapping("/home")
    // Principal - текущий залогиненный пользователь
    public String homePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("image", userDetails.getUser().getImage());
        model.addAttribute("currentUser", userDetails.getUser());
        return "home";
    }

    @PostMapping("/user/update")
    public void updateUser(User userUpdated, @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam("file") MultipartFile file, Errors errors) throws IOException
    {

        User userCurrent = userDetails.getUser();
        userService.updateUser(userCurrent, userUpdated, file);

    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/became/author")
    public String becameAuthor(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        userService.becameAuthor(userDetails.getUser());
        model.addAttribute("currentUser", userDetails.getUser());
        model.addAttribute("image", userDetails.getUser().getImage());
        return "authorPage";
    }
}
