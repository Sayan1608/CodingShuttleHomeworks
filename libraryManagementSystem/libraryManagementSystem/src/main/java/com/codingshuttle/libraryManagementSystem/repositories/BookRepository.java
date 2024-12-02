package com.codingshuttle.libraryManagementSystem.repositories;

import com.codingshuttle.libraryManagementSystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
