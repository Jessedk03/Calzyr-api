package com.calzyr.controllers;

import com.calzyr.dto.ArchiveRecordsModel;
import com.calzyr.repositories.ArchiveRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record-archive")
public class ArchiveRecordsController {

    @Autowired
    private ArchiveRecordsRepository archiveRecordsRepository;

    @GetMapping
    public Iterable<ArchiveRecordsModel> getAllArchivedRecords() { return archiveRecordsRepository.findAllEvents(); }

    @GetMapping("/{record_type}")
    public Iterable<ArchiveRecordsModel> getAllArchivedRecordsByRecordType(@RequestBody String record_type) { return archiveRecordsRepository.findByRecordType(record_type); }

    @PostMapping
    public ArchiveRecordsModel archiveRecord(@RequestBody ArchiveRecordsModel archiveRecord) { return archiveRecordsRepository.save(archiveRecord); }
}
