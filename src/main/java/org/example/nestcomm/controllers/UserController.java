package org.example.nestcomm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.dto.UserDto;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @PostMapping("/user/registration/new")
    public String registrationUser(@Validated UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Bad validation in registration");
            model.addAttribute("errors", bindingResult.getFieldError().getDefaultMessage());
            return "login";
        }
        if(userService.createUser(userDto))
            return "redirect:/login";
        return "login";
    }

    @GetMapping("/home")
    // Principal - текущий залогиненный пользователь
    public String homePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("image", userDetails.getUser().getImage());
        model.addAttribute("currentUser", userDetails.getUser());
        return "home";
    }

    @PostMapping("/user/update")
    public String updateUser(@Validated UserDto userDto, @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) throws IOException
    {
        if (bindingResult.hasErrors()) {
            log.info("Bad validation in update");
            model.addAttribute("errors", bindingResult.getFieldError().getDefaultMessage());
            // TODO придумать как выводить ошибки
            return "redirect:/home";
        }
        User userCurrent = userDetails.getUser();
        userService.updateUser(userCurrent, userDto, file);
        model.addAttribute("currentUser", userDetails.getUser());
        return "home";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/became/author")
    public String becameAuthor(@AuthenticationPrincipal UserDetails userDetails) {
        userService.becameAuthor(userDetails.getUser());
        return "redirect:/home";
    }

    @PostMapping("/user/passwordChange/new")
    public String changePassword(@Validated UserDto userDto, @RequestParam String passwordCheck , BindingResult bindingResult,
                                 Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            log.info("Bad validation in passwordChange");
            model.addAttribute("errors", bindingResult.getFieldError().getDefaultMessage());
            // TODO придумать как выводить ошибки
            return "redirect:/home";
        }
        if(!userDto.getPassword().equals(passwordCheck)){
            log.info("Password does not match");
            model.addAttribute("errors", "Password does not match");
            // TODO придумать как выводить ошибки
            return "redirect:/home";
        }
        userService.changePassword(userDetails.getUser(),userDto);
        return "redirect:/home";

    }
}
