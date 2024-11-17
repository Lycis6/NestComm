package org.example.nestcomm.services;
import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.dto.ProductDto;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.models.Image;
import org.example.nestcomm.models.User;
import org.example.nestcomm.repositories.ImageRepositoryInt;
import org.example.nestcomm.repositories.ProductRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {
    private final ProductRepositoryInt productRepository;

    @Autowired
    ProductService(ProductRepositoryInt productRepository, ImageRepositoryInt imageRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList(){return productRepository.findAll();}

    public List<Product> getListByName(String name) {
        if(name != null) {
            List<Product> products = productRepository.findByName(name);
            if(products.isEmpty()) return productRepository.findAll();
            return products;
        }
        return productRepository.findAll();
    }

    public void saveProduct(ProductDto productDto, MultipartFile file1, MultipartFile file2, MultipartFile file3
                            , UserDetails userDetails) throws IOException {
        Image image1, image2, image3;
        Product product = new Product();
        product.transferDtoToModel(productDto);
        if(!file1.isEmpty()){
            image1 = ToImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImage(image1);
        }
        if(!file2.isEmpty()){
            image2 = ToImageEntity(file2);
            product.addImage(image2);
        }
        if(!file3.isEmpty()){
            image3 = ToImageEntity(file3);
            product.addImage(image3);
        }
        product.setUser(userDetails.getUser());
        log.info("Saving product: Title {}; Author {}", product.getName(), product.getAuthor());
        Product savedProduct = productRepository.save(product);
        savedProduct.setPreviewImageId(product.getImages().get(0).getId());
        productRepository.save(savedProduct);

    }

    public Product getProductById(Long id){
        if(productRepository.findById(id).isEmpty()){
            log.info("Product with id {} not found", id);
            // TODO написать exception
            return null;
        }
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long id, UserDetails userDetails){
        log.info("Deleting product: {}", id);
        Product product = productRepository.findById(id).get();
        if(!Objects.equals(product.getUser().getID(), userDetails.getUser().getID())){
            log.info("User does not have permission to delete product: {}", id);
            // TODO написать exception
            return;
        }
        productRepository.delete(product);
    }

    Image ToImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setData(file.getBytes());
        return image;
    }

}
