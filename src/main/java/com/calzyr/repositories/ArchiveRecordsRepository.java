package com.calzyr.repositories;

import com.calzyr.dto.ArchiveRecordsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArchiveRecordsRepository extends CrudRepository<ArchiveRecordsModel, Integer> {

    @Query("SELECT ar FROM ArchiveRecordsModel ar")
    Iterable<ArchiveRecordsModel> findAllEvents();
}
