package com.homework.codingshuttle.jpa.mappings.controllers;

import com.homework.codingshuttle.jpa.mappings.entities.Professor;
import com.homework.codingshuttle.jpa.mappings.entities.Student;
import com.homework.codingshuttle.jpa.mappings.entities.Subject;
import com.homework.codingshuttle.jpa.mappings.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @PostMapping
    public Professor createNewProfessor(@RequestBody Professor professor){
       return professorService.createNewProfessor(professor);
    }

    @GetMapping(path = "/{professorId}")
    public Professor getProfessorById(@PathVariable(name = "professorId") Long id){
       return professorService.getProfessorById(id);
    }

    @PutMapping(path = "/professor/{professorId}/student/{studentId}")
    public Professor assignStudentToProfessor(
            @PathVariable(name = "professorId") Long professorId,
            @PathVariable(name = "studentId") Long studentId){
        return professorService.assignStudentToProfessor(professorId,studentId);
    }

    @GetMapping(path = "/students/{professorId}")
    public Set<Student> getStudentsAssignedToProfessorById(@PathVariable(name = "professorId") Long id){
        return professorService.getStudentsAssignedToProfessorById(id);
    }

    @PutMapping(path = "/professor/{professorId}/subject/{subjectId}")
    public Professor assignSubjectToProfessor(
            @PathVariable(name = "professorId") Long professorId,
            @PathVariable(name = "subjectId") Long subjectId){
        return professorService.assignSubjectToProfessor(professorId,subjectId);
    }

    @GetMapping(path = "/subject/{subjectId}")
    public Professor getProfessorAssignedToSubject(@PathVariable(name = "subjectId") Long id){
        return professorService.getProfessorAssignedToSubject(id);
    }
}
