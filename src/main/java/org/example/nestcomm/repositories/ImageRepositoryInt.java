package org.example.nestcomm.repositories;

import org.example.nestcomm.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositoryInt extends JpaRepository<Image, Long> {

}
