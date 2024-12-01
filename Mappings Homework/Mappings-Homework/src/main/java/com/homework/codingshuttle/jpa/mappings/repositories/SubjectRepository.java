package com.homework.codingshuttle.jpa.mappings.repositories;

import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
