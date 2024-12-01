package com.homework.codingshuttle.jpa.mappings.controllers;

import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createNewStudent(@RequestBody Student student){
        return studentService.createNewStudent(student);
    }

    @GetMapping(path = "/{studentId}")
    public Student getStudentById(@PathVariable(name = "studentId") Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping(path = "/{studentId}/admission-record/{admissionRecordId}")
    public Student assignAdmissionRecordToStudent(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "admissionRecordId") Long admissionRecordId){
        return studentService.assignAdmissionRecordToStudent(studentId,admissionRecordId);
    }

    @PutMapping(path = "/{studentId}/subjects/{subjectId}")
    public Student assignSubjectToStudent(
           @PathVariable(name = "studentId") Long studentId,
           @PathVariable(name = "subjectId") Long subjectId){
        return studentService.assignSubjectToStudent(studentId,subjectId);
    }
}
