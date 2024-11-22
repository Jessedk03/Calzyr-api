package com.calzyr.controllers;

import com.calzyr.dto.ArchiveRecordDTO;
import com.calzyr.repositories.ArchiveRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record-archive")
public class ArchiveRecordController {

    @Autowired
    private ArchiveRecordRepository archiveRecordRepository;

    @GetMapping
    public Iterable<ArchiveRecordDTO> getAllArchivedRecords() { return archiveRecordRepository.findAllEvents(); }

    @GetMapping("/{record_type}")
    public Iterable<ArchiveRecordDTO> getAllArchivedRecordsByRecordType(@RequestBody String record_type) { return archiveRecordRepository.findByRecordType(record_type); }

    @PostMapping
    public ArchiveRecordDTO archiveRecord(@RequestBody ArchiveRecordDTO archiveRecord) { return archiveRecordRepository.save(archiveRecord); }
}
