package com.evilcorp.repository;

import com.evilcorp.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomAuthorRepository {
    List<Author> findByFilter(String name, String nationality);
}
