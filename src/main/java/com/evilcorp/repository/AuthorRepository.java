package com.evilcorp.repository;

import com.evilcorp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByName(String name);

    List<Author> findByNameAndNationality(String name, String nationality);

}
