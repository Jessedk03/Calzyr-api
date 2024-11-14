package com.calzyr.services;

import com.calzyr.dto.ArchiveRecordsModel;
import com.calzyr.dto.EventsModel;
import com.calzyr.dto.UsersModel;
import com.calzyr.repositories.ArchiveRecordsRepository;
import com.calzyr.repositories.EventsRepository;
import com.calzyr.repositories.UsersRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ArchivingService {

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;
    private final ArchiveRecordsRepository archiveRecordsRepository;

    public ArchivingService(EventsRepository eventsRepository, UsersRepository usersRepository, ArchiveRecordsRepository archiveRecordsRepository) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.archiveRecordsRepository = archiveRecordsRepository;
    }

    @Scheduled(cron = "0 0 2 * *")
    public void archiveSoftDeletedRecords() {
        LocalDateTime fourteenDaysAgo = LocalDateTime.now().minusDays(14);

        List<EventsModel> oldEvents = eventsRepository.findAllDeletedEvents(fourteenDaysAgo);
        List<UsersModel> oldUsers = usersRepository.findAllInactiveUsers(fourteenDaysAgo);

        ArchiveRecordsModel archiveRecord = new ArchiveRecordsModel();

        oldEvents.forEach(event -> {
            archiveRecord.setRecordType("event");
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(event.getTitle());
            archiveRecord.setModifiedUser("Administrator");
            archiveRecord.setModifiedUserId(10);
            archiveRecordsRepository.save(archiveRecord);
            eventsRepository.delete(event);
        });

        oldUsers.forEach(user -> {
            archiveRecord.setRecordType("user");
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(user.getEmail());
            archiveRecord.setModifiedUser("Administrator");
            archiveRecord.setModifiedUserId(10);
            archiveRecordsRepository.save(archiveRecord);
            usersRepository.delete(user);
        });
    }
}
