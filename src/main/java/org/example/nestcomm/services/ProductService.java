package org.example.nestcomm.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.repositories.ProductRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {
    private final ProductRepositoryInt productRepository;

    @Autowired
    ProductService(ProductRepositoryInt productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList(){return productRepository.findAll();}

    public void saveProduct(Product product){
        log.info("Saving product: {}", product);
        productRepository.save(product);
    }

    public Product getProductById(Long id){
        if(productRepository.findById(id).isEmpty()){
            return null;
        }
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long id){
        log.info("Deleting product: {}", id);
        productRepository.deleteById(id);
    }
}
