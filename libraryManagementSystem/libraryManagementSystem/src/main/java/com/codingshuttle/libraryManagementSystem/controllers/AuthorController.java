package com.codingshuttle.libraryManagementSystem.controllers;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody @Valid AuthorDto authorDto){
        return new ResponseEntity<>(authorService.createNewAuthor(authorDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<AuthorDto>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping(path = "{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable(name = "authorId") Long id){
        Optional<AuthorDto> authorDto =  authorService.getAuthorById(id);
        return authorDto
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("Author Not Found with id : " + id));
    }

    @PutMapping(path = "/{authorId}")
    public ResponseEntity<AuthorDto> updateAuthorById(
            @PathVariable(name = "authorId") Long id,
            @RequestBody @Valid AuthorDto authorDto){
        return ResponseEntity.ok(authorService.updateAuthorById(id,authorDto));
    }

    @PutMapping(path = "/author/{authorId}/book/{bookId}")
    public ResponseEntity<AuthorDto> assignBookToAuthor(
            @PathVariable(name = "authorId") Long authorId,
            @PathVariable(name = "bookId") Long bookId){
        return ResponseEntity.ok(authorService.assignBookToAuthor(authorId,bookId));
    }

    @GetMapping(path = "/book/{bookId}")
    public ResponseEntity<AuthorDto> getBookAuthor(@PathVariable(name = "bookId") Long id){
        return ResponseEntity.ok(authorService.getBookAuthor(id));
    }

    @DeleteMapping(path = "/{authorId}")
    public ResponseEntity<Boolean> deleteAuthorById(@PathVariable(name = "authorId") Long id){
        return ResponseEntity.ok(authorService.deleteAuthorById(id));
    }

    @GetMapping(path = "/name/{authorName}")
    public ResponseEntity<Set<AuthorDto>> getAuthorByName(@PathVariable(name = "authorName") String name){
        return ResponseEntity.ok(authorService.getAuthorByName(name));
    }
}
