package com.codingshuttle.libraryManagementSystem.repositories;

import com.codingshuttle.libraryManagementSystem.entities.AuthorEntity;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    Set<BookEntity> findByBookAuthor(AuthorEntity author);

    Optional<BookEntity> findByTitle(String title);

    Set<BookEntity> findByPublishedOnAfter(LocalDate date);
}
