package com.codingshuttle.libraryManagementSystem.services;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {
    @Autowired
    private ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createNewAuthor(AuthorDto authorDto) {
        return null;
    }

    public Set<AuthorDto> getAllAuthors() {
        return null;
    }

    public Optional<AuthorDto> getAuthorById(Long id) {
        return null;
    }
}
