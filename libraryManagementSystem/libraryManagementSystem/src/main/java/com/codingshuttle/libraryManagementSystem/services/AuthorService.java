package com.codingshuttle.libraryManagementSystem.services;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.entities.AuthorEntity;
import com.codingshuttle.libraryManagementSystem.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createNewAuthor(AuthorDto authorDto) {
        AuthorEntity authorEntity = modelMapper.map(authorDto, AuthorEntity.class);
        return modelMapper.map(authorRepository.save(authorEntity), AuthorDto.class);
    }

    public Set<AuthorDto> getAllAuthors() {
        List<AuthorEntity> authors = authorRepository.findAll();
        return authors
                .stream()
                .map(author->modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toSet());
    }

    public Optional<AuthorDto> getAuthorById(Long id) {
        Optional<AuthorEntity> authorEntity = authorRepository.findById(id);
        return authorEntity
                .map(author->modelMapper.map(author, AuthorDto.class));
    }
}
