package com.evilcorp.repository.impl;

import com.evilcorp.model.Author;
import com.evilcorp.repository.CustomAuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Author> findByFilter(String name, String nationality) {
        String queryText;
        TypedQuery<Author> query;

        if (name == null) {
            queryText = "select au " +
                    "from Author au " +
                    "where au.nationality = :nationality";
            query = em.createQuery(queryText, Author.class);
            query.setParameter("nationality", nationality);
            return query.getResultList();
        } else if (nationality == null) {
            queryText = "select au " +
                    "from Author au " +
                    "where au.name = :name";
            query = em.createQuery(queryText, Author.class);
            query.setParameter("nationality", nationality);
            return query.getResultList();
        }
        queryText = "select au " +
                "from Author au " +
                "where au.name = :name and " +
                "au.nationality = :nationality";
        query = em.createQuery(queryText, Author.class);
        query.setParameter("name", name);
        query.setParameter("nationality", nationality);
        return query.getResultList();
    }
}
