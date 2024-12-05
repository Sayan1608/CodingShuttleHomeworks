package com.codingshuttle.libraryManagementSystem.repositories;

import com.codingshuttle.libraryManagementSystem.entities.AuthorEntity;
import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {
    AuthorEntity findByPublishedBooks(BookEntity bookEntity);

    Set<AuthorEntity> findByName(String name);
}
