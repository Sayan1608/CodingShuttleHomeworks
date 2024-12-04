package com.codingshuttle.libraryManagementSystem.services;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.entities.AuthorEntity;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.repositories.AuthorRepository;
import com.codingshuttle.libraryManagementSystem.repositories.BookRepository;
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
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;

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

    public void isExistsAuthorById(Long id){
        if(!authorRepository.existsById(id)) throw new ResourceNotFoundException("Author not found with id :" +id);
    }

    public AuthorDto updateAuthorById(Long id, AuthorDto authorDto) {
        isExistsAuthorById(id);
        authorDto.setId(id);
        AuthorEntity savedAuthor = authorRepository.save(modelMapper.map(authorDto, AuthorEntity.class));
        return modelMapper.map(savedAuthor, AuthorDto.class);

    }

    public AuthorDto assignBookToAuthor( Long authorId,Long bookId) {
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
        Optional<AuthorEntity> authorEntity = authorRepository.findById(authorId);

        AuthorEntity mappedAuthorEntity = authorEntity.flatMap(author ->
                bookEntity.map(
                        book -> {
                            book.setBookAuthor(author);
                            bookRepository.save(book);
                            author.getPublishedBooks().add(book);
                            return author;
                        }
                )
        ).orElseThrow(() -> new ResourceNotFoundException("Author not found with id : " + bookId));
        return modelMapper.map(mappedAuthorEntity, AuthorDto.class);
    }


    public AuthorDto getBookAuthor(Long bookId) {
       BookEntity bookEntity = BookEntity.builder()
               .id(bookId)
               .build();

        AuthorEntity authorDto = authorRepository.findByPublishedBooks(bookEntity);
        return modelMapper.map(authorDto, AuthorDto.class);

    }
}
