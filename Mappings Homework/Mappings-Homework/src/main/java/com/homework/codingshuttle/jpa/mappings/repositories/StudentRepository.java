package com.homework.codingshuttle.jpa.mappings.repositories;

import com.homework.codingshuttle.jpa.mappings.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
