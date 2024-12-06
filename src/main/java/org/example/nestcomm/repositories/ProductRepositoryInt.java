package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryInt extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryAndPriceBetween(String category, int minPrice, int maxPrice);
    List<Product> findByPriceBetween(int minPrice, int maxPrice);
    Optional<Product> findProductByID(Long id);
}
