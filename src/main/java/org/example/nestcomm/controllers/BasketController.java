package org.example.nestcomm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.Basket;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.models.User;
import org.example.nestcomm.services.BasketService;
import org.example.nestcomm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class BasketController {
    private final BasketService basketService;
    private final ProductService productService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;

    }

    @GetMapping("/basket")
    public String basketPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Basket userBasket = basketService.getBasketById(userDetails.getUser().getBasket().getId());
        List<Long> listCounts = new ArrayList<>();
        Long[][] filteredIdsAndCounts = userBasket.TransferStringToLong();
        if(filteredIdsAndCounts == null) {
            model.addAttribute("products", null);
            model.addAttribute("counts", null);
            return "/basket";
        }
        int i = 0;
        int j = 0;
        while(filteredIdsAndCounts[i][0] != null) {
            i++;
        }
        Long[] ids = new Long[i];
        while(j < i){
            ids[j] = filteredIdsAndCounts[j][0];
            listCounts.add(filteredIdsAndCounts[j][1]);
            j++;
        }
        model.addAttribute("products", productService.getListByIds(ids));
        model.addAttribute("counts", listCounts);
        return "/basket";
    }

    @GetMapping("basket/add/{productId}")
    public String addProductToBasket(@PathVariable(name = "productId") Long productId,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        log.info("Adding product to Basket in controller layer");
        User user = userDetails.getUser();
        if(basketService.addProductToBasket(productId, user.getBasket().getId()))
            log.info("Product added to Basket");
        else
            log.info("Product not added to Basket");
        return "redirect:/product/" + productId;
    }

    @GetMapping("basket/delete/{productId}")
    public String deleteProductFromBasket(@PathVariable(name = "productId")Long productId,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        if(basketService.deleteProductFromBasket(productId,userDetails.getUser().getBasket().getId()))
            log.info("Product deleted from Basket");
        return "redirect:/basket";
    }

    @PostMapping("basket/setAmount/{productId}")
    public String setAmount(@PathVariable(name = "productId")Long productId, @RequestParam(name = "amount") int amount,
                            @AuthenticationPrincipal UserDetails userDetails)
    {
        if(!basketService.setAmount(productId, userDetails.getUser().getBasket().getId(), amount))
            log.info("Amount can't be corrected for product {}",productId);
        return "redirect:/basket";
    }


}
