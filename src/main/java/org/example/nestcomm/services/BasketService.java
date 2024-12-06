package org.example.nestcomm.services;

import org.example.nestcomm.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.models.Basket;
import org.example.nestcomm.repositories.BasketRepositoryInt;
import org.example.nestcomm.repositories.ProductRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class BasketService {
    private final BasketRepositoryInt basketRepository;
    private final ProductRepositoryInt productRepository;
    @Autowired
    public BasketService(BasketRepositoryInt basketRepository, ProductRepositoryInt productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    synchronized public boolean addProductToBasket(Long productId, Long basketId) {
        Optional<Basket> optional1 = basketRepository.findBasketById(basketId);
        Optional<Product> optional2 = productRepository.findProductByID(productId);
        if (optional1.isPresent() && optional2.isPresent()) {
            Basket basket = optional1.get();
            Product product = optional2.get();
            if(!product.IsInStock()){
                log.info("Product is not in stock");
                return false;
            }
            log.info("Adding product to Basket in service layer");
            basket.setTotalPrice(basket.getTotalPrice() + product.getPrice());
            if(Objects.equals(basket.getOrderedProductIds(), ""))
                basket.setOrderedProductIds("," + basket.getId().toString() + ",");
            else {
                basket.setOrderedProductIds(basket.getOrderedProductIds() + product.getID().toString() + ",");
            }
            basketRepository.save(basket);
            log.info("Updating product balance");
            product.setBalance(product.getBalance() - 1);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    synchronized public boolean deleteProductFromBasket(Long productId, Long basketId) {
        Optional<Basket> optionalBasket = basketRepository.findBasketById(basketId);
        Optional<Product> optionalProduct = productRepository.findProductByID(productId);
        if(optionalBasket.isPresent() && optionalProduct.isPresent()){
            Basket basket = optionalBasket.get();
            Product product = optionalProduct.get();
            String orderedProductIds = basket.getOrderedProductIds();
            int i = 0; // счетчик продуктов в корзине
            while(true) {
                if (orderedProductIds.contains("," + productId.toString() + ",")) {
                    orderedProductIds = orderedProductIds.replaceFirst("," + productId.toString() + ",", ",");
                    basket.setTotalPrice(basket.getTotalPrice() - product.getPrice());
                    i++;
                } else {
                    log.info("Now product is not in basket");
                    product.setBalance(product.getBalance() + i);
                    basket.setOrderedProductIds(orderedProductIds);
                    basketRepository.save(basket);
                    productRepository.save(product);
                    return true;
                }
            }
        }
        else{
            log.info("Basket or Product not found");
            return false;
        }
    }

    synchronized public boolean setAmount(Long productId, Long basketId, int amount) {
        Optional<Product> optionalProduct = productRepository.findProductByID(productId);
        Optional<Basket> optionalBasket = basketRepository.findBasketById(basketId);
        if(optionalProduct.isPresent() && optionalBasket.isPresent()){
            Product product = optionalProduct.get();
            if(product.getBalance() - amount < 0){
                log.info("There is no such amount of product");
                return false;
            }
            deleteProductFromBasket(productId, basketId);
            for(int i = 0; i < amount; i++)
                addProductToBasket(productId, basketId);
        }
        else{
            log.info("Product or Basket does not exist");
            return false;
        }
        return true;
    }

    public Basket getBasketById(Long BasketId) {
        Optional<Basket> optional = basketRepository.findBasketById(BasketId);
        return optional.orElse(null);
    }
}
