package com.homework.codingshuttle.jpa.mappings.services;

import com.homework.codingshuttle.jpa.mappings.entities.AdmissionRecord;
import com.homework.codingshuttle.jpa.mappings.repositories.AdmissionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordService {
    private final AdmissionRecordRepository admissionRecordRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
    }

    public AdmissionRecord createNewAdmissionRecord(AdmissionRecord admissionRecord) {
        return admissionRecordRepository.save(admissionRecord);
    }

    public AdmissionRecord getAdmissionRecordById(Long id) {
        Optional<AdmissionRecord> admissionRecordEntity = admissionRecordRepository.findById(id);
        return admissionRecordEntity.orElse(null);
    }
}
