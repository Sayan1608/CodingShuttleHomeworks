package com.homework.codingshuttle.jpa.mappings.repositories;

import com.homework.codingshuttle.jpa.mappings.entities.Professor;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    Professor findBySubjectsAssigned(Subject subject);
}
