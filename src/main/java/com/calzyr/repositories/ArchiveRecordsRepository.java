package com.calzyr.repositories;

import com.calzyr.dto.ArchiveRecordsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArchiveRecordsRepository extends CrudRepository<ArchiveRecordsModel, Integer> {

    @Query("SELECT ar FROM ArchiveRecordsModel ar")
    Iterable<ArchiveRecordsModel> findAllEvents();

    @Query("SELECT ar FROM ArchiveRecordsModel ar WHERE ar.RecordType = :record_type")
    Iterable<ArchiveRecordsModel> findByRecordType(@Param("record_type") String record_type);
}
