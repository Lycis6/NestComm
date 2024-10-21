package org.example.nestcomm.repositories;

import org.example.nestcomm.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ProductImageRepositoryInt extends JpaRepository<ProductImage, Long> {

}
