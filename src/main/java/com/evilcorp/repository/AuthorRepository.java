package com.evilcorp.repository;

import com.evilcorp.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
    List<Author> findByName(String name);

}
