package com.codingshuttle.libraryManagementSystem.controllers;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.services.AuthorService;
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
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody AuthorDto authorDto){
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
}
