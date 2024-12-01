package com.homework.codingshuttle.jpa.mappings.controllers;

import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import com.homework.codingshuttle.jpa.mappings.services.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject createNewSubject(@RequestBody Subject subject){
        return subjectService.createNewSubject(subject);
    }

    @GetMapping(path = "/{subjectId}")
    public Subject getSubjectById(@PathVariable(name = "subjectId") Long id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping(path = "/students/{subjectId}")
    public Set<Student> getEnrolledStudentsBySubjectId(@PathVariable(name="subjectId")Long id){
        return subjectService.getEnrolledStudentsBySubjectId(id);
    }


}
