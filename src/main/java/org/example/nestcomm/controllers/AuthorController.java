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

    // возвращает авторскую страницу
    @GetMapping("/author/{email}")
    public String authorPage(@PathVariable String email, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User author = userService.findByEmail(email).get();
        model.addAttribute("author", author);
        model.addAttribute("image", author.getImage());
        model.addAttribute("listOfGoods", author.getProducts());
        model.addAttribute("currentUser", userDetails.getUser());
        return "authorPage";
    }
}
