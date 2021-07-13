package com.twb.book.controller;

import com.twb.book.model.Author;
import com.twb.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false) String name) {
        List result = StringUtils.isEmpty(name) ? StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()): authorRepository.findAllByNameUsingAnnotations(name);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id)  {
        Author result = authorRepository.findById(id).orElse(null);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) throws URISyntaxException {
        Author result = authorRepository.save(author);
        return ResponseEntity.created(new URI("/api/v1/authors" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") String id, @RequestBody Author author) {
        final Author updatedEmployee = authorRepository.save(author);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") String id) {
        Author tutotial = authorRepository.findById(id)
                .orElse(null);

        if(tutotial!=null)
            authorRepository.delete(tutotial);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}