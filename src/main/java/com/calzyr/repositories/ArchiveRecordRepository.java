package com.calzyr.repositories;

import com.calzyr.dto.ArchiveRecordDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArchiveRecordRepository extends CrudRepository<ArchiveRecordDTO, Integer> {

    @Query("SELECT ar FROM ArchiveRecordDTO ar")
    Iterable<ArchiveRecordDTO> findAllEvents();

    @Query("SELECT ar FROM ArchiveRecordDTO ar WHERE ar.RecordType = :record_type")
    Iterable<ArchiveRecordDTO> findByRecordType(@Param("record_type") String record_type);
}
