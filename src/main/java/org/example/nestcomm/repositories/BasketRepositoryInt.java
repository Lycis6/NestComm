package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepositoryInt extends JpaRepository<Basket, Long> {
    Optional<Basket> findBasketById(Long id);
}
