package org.example.nestcomm.services;
import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.configurations.UserDetails;
import org.example.nestcomm.models.Product;
import org.example.nestcomm.models.Image;
import org.example.nestcomm.models.User;
import org.example.nestcomm.repositories.ProductRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepositoryInt productRepository;

    @Autowired
    ProductService(ProductRepositoryInt productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getList(){return productRepository.findAll();}

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3
                            , UserDetails userDetails) throws IOException {
        Image image1, image2, image3;
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
            return null;
        }
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long id){
        log.info("Deleting product: {}", id);
        productRepository.deleteById(id);
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
