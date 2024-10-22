package org.example.nestcomm.repositories;

import org.example.nestcomm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInt extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
