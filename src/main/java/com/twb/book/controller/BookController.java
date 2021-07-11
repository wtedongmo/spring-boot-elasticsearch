package com.twb.book.controller;

import com.twb.book.model.Book;
import com.twb.book.service.BookService;
import io.netty.util.internal.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        if(book.getId()==null) return null;
        return bookService.save(book);
    }

    @GetMapping
    public List<Book> getAllBooks(){

        return StreamSupport.stream(bookService.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id){

        return bookService.findOne(id);
    }

    @GetMapping("/author")
    public Page<Book> getBookAuthor(@RequestParam String author, PageRequest page){

        return bookService.findByAuthor(author, page);
    }

    @GetMapping("/title")
    public List<Book> getBookTitle(@RequestParam String title){

        return bookService.findByTitle(title);
    }

    @GetMapping("/all/title")
    public List<Book> getAllByTitle(@RequestParam String title){

        return bookService.findAllByTitle(title);
    }

    @GetMapping("/_search")
    public List<Book> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
