package com.calzyr.repositories;

import com.calzyr.entities.archive.ArchiveRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRecordRepository extends CrudRepository<ArchiveRecord, Integer> {

    @Query("SELECT ar FROM ArchiveRecord ar")
    Iterable<ArchiveRecord> findAllEvents();

    @Query("SELECT ar FROM ArchiveRecord ar WHERE ar.RecordType = :record_type")
    Iterable<ArchiveRecord> findByRecordType(@Param("record_type") String record_type);
}
