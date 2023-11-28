package com.evilcorp.controller;

import com.evilcorp.model.Author;
import com.evilcorp.model.Book;
import com.evilcorp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable UUID id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.map(author ->
                new ResponseEntity<>(author, HttpStatus.OK)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable UUID id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.map(author ->
                new ResponseEntity<>(author.getBooks(), HttpStatus.OK)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

//    @GetMapping("/author/{name}")
//    public ResponseEntity<List<Author>> getAuthorsForName(@PathVariable String name) {
//        List<Author> resultAuthor = new ArrayList<>();
//        Iterable<Author> iterableAuthor = authorRepository.findAll();
//        Iterator<Author> iteratorAuthor = iterableAuthor.iterator();
//        while (iteratorAuthor.hasNext()) {
//            Author author = iteratorAuthor.next();
//            if (author.getName().equalsIgnoreCase(name)) {
//                resultAuthor.add(author);
//            }
//        }
//        return new ResponseEntity<List<Author>>(resultAuthor, HttpStatus.OK);
//    }
    @GetMapping("/author/{name}")
    public List<Author> getAuthorsByName(@PathVariable String name) {
        return authorRepository.findByName(name);
    }

    @GetMapping("/author/{name}/{nationality}")
    public List<Author> getAuthorsByNameAndNationality(@PathVariable("name") String name,
                                             @PathVariable("nationality") String nationality) {
        return authorRepository.findByNameAndNationality(name, nationality);
    }

    @PostMapping("/add-author")
    public UUID add(@RequestBody Author author) {
        Author newAuthor = authorRepository.save(author);
        return newAuthor.getId();
    }
}