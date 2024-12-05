package com.codingshuttle.libraryManagementSystem.services;

import com.codingshuttle.libraryManagementSystem.dtos.AuthorDto;
import com.codingshuttle.libraryManagementSystem.dtos.BookDto;
import com.codingshuttle.libraryManagementSystem.entities.AuthorEntity;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import com.codingshuttle.libraryManagementSystem.exceptions.InvalidPayloadException;
import com.codingshuttle.libraryManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.libraryManagementSystem.repositories.AuthorRepository;
import com.codingshuttle.libraryManagementSystem.repositories.BookRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    private Validator validator;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, AuthorRepository authorRepository1) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository1;
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

    public void isExistsBookById(Long id){
        if(!bookRepository.existsById(id)) throw new ResourceNotFoundException("Book not found with id : " + id);
    }

    public BookDto updateBookById(Long id, @Valid BookDto bookDto) {
        isExistsBookById(id);
        bookDto.setId(id);
        BookEntity savedBook = bookRepository.save(modelMapper.map(bookDto, BookEntity.class));
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBookPartially(Long id, Map<String, Object> bookUpdates) {
        isExistsBookById(id);
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
       return bookEntity.map(book->{
            bookUpdates.forEach((key,value)->{
                Field field = ReflectionUtils.findRequiredField(BookEntity.class, key);
                field.setAccessible(true);
                // Handle LocalDate explicitly
                if ("publishedOn".equals(key) && value instanceof String) {
                    value = LocalDate.parse((String) value); // Convert String to LocalDate
                }
                ReflectionUtils.setField(field,book,value);
            });
           // Validate the entity
           Set<ConstraintViolation<BookDto>> violations = validator.validate(modelMapper.map(book, BookDto.class));
           if (!violations.isEmpty()) {
               Map<String, String> errors = violations.stream()
                       .collect(Collectors.toMap(
                               v -> v.getPropertyPath().toString(),
                               ConstraintViolation::getMessage
                       ));
//               return ResponseEntity.badRequest().body(errors);
               throw new InvalidPayloadException("Invalid payload found.Please note the following errors",errors);
           }
           return modelMapper.map(bookRepository.save(book), BookDto.class);
        }).orElseThrow(()->new ResourceNotFoundException("Book not found with id : " + id));

    }

    public List<BookEntity> getAllBooksAll() {
        return bookRepository.findAll();
    }

    public Set<BookDto> getBooksPublishedByAuthor(Long authorId) {
        if(authorRepository.existsById(authorId)){
            AuthorEntity authorEntity = AuthorEntity.builder()
                    .id(authorId)
                    .build();
            Set<BookEntity> bookEntitySet = bookRepository.findByBookAuthor(authorEntity);
            return bookEntitySet
                    .stream()
                    .map(book->modelMapper.map(book, BookDto.class))
                    .collect(Collectors.toSet());
        }else{
            throw new ResourceNotFoundException("Author not found with id : " + authorId);
        }

    }

    public Boolean deleteBookById(Long id) {
        isExistsBookById(id);
        bookRepository.deleteById(id);
        return true;
    }

    public BookDto findBookByTitle(String title) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitle(title);
        return bookEntity.map(book->modelMapper.map(book, BookDto.class))
                .orElseThrow(()->new ResourceNotFoundException("Book not found with title : " + title));
    }

    public Set<BookDto> getBooksPublishedAfterDate(LocalDate date) {
        Set<BookEntity> bookEntitySet = bookRepository.findByPublishedOnAfter(date);
        return bookEntitySet
                .stream()
                .map(book->modelMapper.map(book, BookDto.class))
                .collect(Collectors.toSet());
    }
}
