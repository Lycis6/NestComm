package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryInt extends JpaRepository<Role, Long> {
}
