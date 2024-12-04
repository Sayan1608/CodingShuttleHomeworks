package com.codingshuttle.libraryManagementSystem.controllers;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<BookDto> createNewBook(@RequestBody @Valid BookDto bookDto){
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

    @PutMapping(path = "/{bookId}")
    public ResponseEntity<BookDto> updateBookById(
            @PathVariable(name = "bookId") Long id,
            @RequestBody @Valid BookDto bookDto){
        return ResponseEntity.ok(bookService.updateBookById(id,bookDto));
    }

    @PatchMapping(path = "/{bookId}")
    public ResponseEntity<BookDto> updateBookPartially(
            @PathVariable(name = "bookId") Long id,
            @RequestBody Map<String, Object> bookUpdates){
        return ResponseEntity.ok(bookService.updateBookPartially(id,bookUpdates));
    }

    @GetMapping(path = "/author/{authorId}")
    public ResponseEntity<Set<BookDto>> getBooksPublishedByAuthor(@PathVariable(name = "authorId") Long id){
        return ResponseEntity.ok(bookService.getBooksPublishedByAuthor(id));
    }

    @GetMapping(path = "/v1")
    public List<BookEntity> getAllBooksAll(){
        return bookService.getAllBooksAll();
    }


}
