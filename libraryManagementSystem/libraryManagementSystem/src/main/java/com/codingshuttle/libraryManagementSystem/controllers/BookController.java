package com.codingshuttle.libraryManagementSystem.controllers;

import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createNewBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createNewBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping(path = "{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "bookId") Long id){
        Optional<BookDto> bookDto =  bookService.getBookById(id);
       return bookDto
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("Book Not Found with id : " + id));
    }
}
