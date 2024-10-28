package org.example.nestcomm.controllers;

import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getProducts(Model model) {
        model.addAttribute("listOfGoods", productService.getList());
        return "product";
    }

    @GetMapping("/product/{id}")
    public String getProductByID(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("images", product.getImages());
        return "productInfo";
    }

    @PostMapping("/product/add")
    public String addProduct(Product product, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
    @RequestParam("file3") MultipartFile file3, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        productService.saveProduct(product, file1, file2, file3, userDetails);
        return "redirect:/product";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
