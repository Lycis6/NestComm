package org.example.nestcomm.controllers;

import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.dto.ProductDto;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getProducts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails != null)
            model.addAttribute("currentUser", userDetails.getUser());
        else{ // продукт смотрит незарегистрированный пользователь
            User emptyUser = new User();
            model.addAttribute("currentUser", emptyUser);
        }
        model.addAttribute("listOfGoods", productService.getList());
        model.addAttribute("Name", "Ivan");
        return "product";
    }

    @GetMapping("/product/{id}")
    public String getProductByID(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Product product = productService.getProductById(id);
        if(userDetails != null)
            model.addAttribute("currentUser", userDetails.getUser());
        else{ // продукт смотрит незарегистрированный пользователь
            User emptyUser = new User();
            model.addAttribute("currentUser", emptyUser);
        }
        model.addAttribute("product",product);
        model.addAttribute("authorId", product.getUser().getID());
        model.addAttribute("images", product.getImages());

        return "productInfo";
    }

    // возвращает список продуктов по названию
    @GetMapping("/product/find")
    public String getProductByName(@RequestParam String name, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails != null)
            model.addAttribute("currentUser", userDetails.getUser());
        else{ // продукт смотрит незарегистрированный пользователь
            User emptyUser = new User();
            model.addAttribute("currentUser", emptyUser);
        }
        model.addAttribute("listOfGoods", productService.getListByName(name));
        return "product";
    }

    @PreAuthorize("hasAnyAuthority('AUTHOR', 'ADMIN')")
    @PostMapping("/product/create/new")
    public String addProduct(@Validated ProductDto productDto, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        productDto.setAuthor(userDetails.getUser().getEmail());
        productService.saveProduct(productDto, file1, file2, file3, userDetails);
        return "redirect:/product";
    }

    @PreAuthorize("hasAnyAuthority('AUTHOR', 'ADMIN')")
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        productService.deleteProduct(id, userDetails);
        return "redirect:/product";
    }

    @PreAuthorize("hasAnyAuthority('AUTHOR', 'ADMIN')")
    @GetMapping("/product/create")
    public String createProduct(){
        return "productCreate";
    }
}
