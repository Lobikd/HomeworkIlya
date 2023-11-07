package com.evilcorp.controller;

import com.evilcorp.model.Author;
import com.evilcorp.model.Book;
import com.evilcorp.repository.AuthorRepository;
import com.evilcorp.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;



    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(book ->
                new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/authors/{id}/books/add-book")
    public void add(@PathVariable(value = "id") UUID authorId,
                    @Valid @RequestBody Book book) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        author.getBooks().add(book);
        book.setAuthor(author);
        authorRepository.save(author);
    }
}
