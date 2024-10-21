package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryInt extends JpaRepository<Product, Long> {
}
