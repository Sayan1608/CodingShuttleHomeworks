package com.homework.codingshuttle.jpa.mappings.services;

import com.homework.codingshuttle.jpa.mappings.entities.AdmissionRecord;
import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import com.homework.codingshuttle.jpa.mappings.repositories.AdmissionRecordRepository;
import com.homework.codingshuttle.jpa.mappings.repositories.StudentRepository;
import com.homework.codingshuttle.jpa.mappings.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, AdmissionRecordRepository admissionRecordRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.admissionRecordRepository = admissionRecordRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student createNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        Optional<Student> studentEntity = studentRepository.findById(id);
        return studentEntity.orElse(null);

    }

    public Student assignAdmissionRecordToStudent(Long studentId, Long admissionRecordId) {
        Optional<Student> studentEntity = studentRepository.findById(studentId);
        Optional<AdmissionRecord> admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId);

       return studentEntity.flatMap(student -> admissionRecordEntity.map(
               admissionRecord -> {
                   student.setAdmissionRecord(admissionRecord);
                   return studentRepository.save(student);

               }
       )).orElse(null);
    }

    public Student assignSubjectToStudent(Long studentId, Long subjectId) {
        Optional<Student> studentEntity = studentRepository.findById(studentId);
        Optional<Subject> subjectEntity = subjectRepository.findById(subjectId);

        return studentEntity.flatMap(student -> subjectEntity.map(
                subject -> {
                    student.getSubjects().add(subject);
                    return studentRepository.save(student);
                })).orElse(null);
    }
}
