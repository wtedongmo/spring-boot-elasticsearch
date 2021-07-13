package com.twb.book.repository;

import com.twb.book.model.Author;
import com.twb.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AuthorRepository extends ElasticsearchRepository<Author, String> {

    @Query("{\"match\":{\"name\":\"?0\"}}")
    List<Author> findAllByNameUsingAnnotations(String name);

}