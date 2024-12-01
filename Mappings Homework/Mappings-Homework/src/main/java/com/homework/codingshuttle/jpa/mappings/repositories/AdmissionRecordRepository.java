package com.homework.codingshuttle.jpa.mappings.repositories;

import com.homework.codingshuttle.jpa.mappings.entities.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord,Long> {
}
