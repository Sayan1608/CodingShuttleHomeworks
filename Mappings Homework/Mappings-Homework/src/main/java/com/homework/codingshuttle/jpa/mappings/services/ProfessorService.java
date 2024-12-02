package com.homework.codingshuttle.jpa.mappings.services;

import com.homework.codingshuttle.jpa.mappings.entities.Professor;
import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import com.homework.codingshuttle.jpa.mappings.repositories.ProfessorRepository;
import com.homework.codingshuttle.jpa.mappings.repositories.StudentRepository;
import com.homework.codingshuttle.jpa.mappings.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }


    public Professor createNewProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor getProfessorById(Long id) {
        Optional<Professor> professorEntity = professorRepository.findById(id);
        return professorEntity.orElse(null);
    }

    public Professor assignStudentToProfessor(Long professorId, Long studentId) {
        Optional<Professor> professorEntity = professorRepository.findById(professorId);
        Optional<Student> studentEntity = studentRepository.findById(studentId);

       return professorEntity.flatMap(professor->studentEntity.map(
               student -> {
                   professor.getStudents().add(student);
                   return professorRepository.save(professor);
               }
       )).orElse(null);
    }

    public Set<Student> getStudentsAssignedToProfessorById(Long id) {
        Optional<Professor> professorEntity = professorRepository.findById(id);
        return professorEntity.map(Professor::getStudents).orElse(null);
    }

    public Professor assignSubjectToProfessor(Long professorId, Long subjectId) {
        Optional<Professor> professorEntity = professorRepository.findById(professorId);
        Optional<Subject> subjectEntity = subjectRepository.findById(subjectId);

        return professorEntity.flatMap(professor -> subjectEntity.map(
                subject ->{
                    subject.setAssignedProfessor(professor);
                    subjectRepository.save(subject);
                    professor.getSubjectsAssigned().add(subject);
                    return professor;
                } )).orElse(null);
    }

    public Professor getProfessorAssignedToSubject(Long id) {
        Optional<Subject> subjectEntity = subjectRepository.findById(id);
        return subjectEntity.map(subject -> professorRepository.findBySubjectsAssigned(subject)).orElse(null);
    }
}
