package org.example.nestcomm.repositories;

import org.example.nestcomm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryInt extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);
}
