package org.example.nestcomm.controllers;

import org.example.nestcomm.models.Product;
import org.example.nestcomm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



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
        model.addAttribute("product",productService.getProductById(id));
        return "productInfo";
    }

    @PostMapping("/product/add")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
