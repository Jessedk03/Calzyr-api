package com.calzyr.controllers;

import com.calzyr.entity.archive.ArchiveRecord;
import com.calzyr.repositories.ArchiveRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record-archive")
public class ArchiveRecordController {

    @Autowired
    private ArchiveRecordRepository archiveRecordRepository;

    @GetMapping
    public Iterable<ArchiveRecord> getAllArchivedRecords() { return archiveRecordRepository.findAllEvents(); }

    @GetMapping("/{record_type}")
    public Iterable<ArchiveRecord> getAllArchivedRecordsByRecordType(@RequestBody String record_type) { return archiveRecordRepository.findByRecordType(record_type); }

    @PostMapping
    public ArchiveRecord archiveRecord(@RequestBody ArchiveRecord archiveRecord) { return archiveRecordRepository.save(archiveRecord); }
}
