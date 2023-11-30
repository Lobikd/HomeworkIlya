package com.evilcorp.repository;

import com.evilcorp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>,
        CustomAuthorRepository {
    List<Author> findByName(String name);

    List<Author> findByNameAndNationality(String name, String nationality);

//    @Query(value = "select au " +
//            "from Author au " +
//            "where (:name is null or au.name = :name) and" +
//            "(:nationality is null or au.nationality = :nationality)")
//    List<Author> findByNameOrNationality(@Param("name") String name, @Param("nationality")String nationality);

}
