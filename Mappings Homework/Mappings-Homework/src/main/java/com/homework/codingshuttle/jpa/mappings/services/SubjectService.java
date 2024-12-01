package com.homework.codingshuttle.jpa.mappings.services;

import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import com.homework.codingshuttle.jpa.mappings.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createNewSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        Optional<Subject> subjectEntity = subjectRepository.findById(id);
        return subjectEntity.orElse(null);
    }

    public Set<Student> getEnrolledStudentsBySubjectId(Long id) {
        Optional<Subject> subjectEntity = subjectRepository.findById(id);
        return subjectEntity.map(Subject::getStudents).orElse(null);
    }
}
