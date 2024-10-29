package org.example.nestcomm.controllers;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AuthorController {
    UserService userService;
    @Autowired
    public AuthorController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/author/{email}")
    public String authorPage(@PathVariable String email, Model model) {
        User user = userService.findByEmail(email).get();
        model.addAttribute("currentUser", user);
        model.addAttribute("image", user.getImage());
        model.addAttribute("listOfGoods", user.getProducts());
        return "authorPage";
    }
}
