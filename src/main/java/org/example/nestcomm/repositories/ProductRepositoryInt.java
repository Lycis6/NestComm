package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryInt extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
