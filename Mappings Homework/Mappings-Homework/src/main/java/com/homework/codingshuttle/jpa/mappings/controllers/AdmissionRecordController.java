package com.homework.codingshuttle.jpa.mappings.controllers;

import com.homework.codingshuttle.jpa.mappings.entities.AdmissionRecord;
import com.homework.codingshuttle.jpa.mappings.services.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admission-records")
public class AdmissionRecordController {
    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }
    @PostMapping
    public AdmissionRecord createNewAdmissionRecord(@RequestBody AdmissionRecord admissionRecord){
        return admissionRecordService.createNewAdmissionRecord(admissionRecord);
    }

    @GetMapping(path = "/{admissionRecordId}")
    public AdmissionRecord getAdmissionRecordById(@PathVariable(name = "admissionRecordId") Long id){
        return admissionRecordService.getAdmissionRecordById(id);
    }
}
