package com.codingshuttle.libraryManagementSystem.services;

import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import com.codingshuttle.libraryManagementSystem.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createNewBook(BookDto bookDto) {
        BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
        BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return modelMapper.map(savedBookEntity, BookDto.class);
    }

    public Set<BookDto> getAllBooks() {
        List<BookEntity> allBooks = bookRepository.findAll();
        return allBooks
                .stream()
                .map(book->modelMapper.map(book,BookDto.class))
                .collect(Collectors.toSet());
    }

    public Optional<BookDto> getBookById(Long id) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
        return bookEntity.map(book-> modelMapper.map(book, BookDto.class));
    }
}
