package com.calzyr.services.common;

import com.calzyr.entities.archive.ArchiveRecord;
import com.calzyr.entities.event.Event;
import com.calzyr.entities.user.User;
import com.calzyr.repositories.ArchiveRecordRepository;
import com.calzyr.repositories.EventRepository;
import com.calzyr.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ArchivingService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ArchiveRecordRepository archiveRecordRepository;
    private final LoggingService loggingService;

    public ArchivingService(EventRepository eventRepository, UserRepository userRepository, ArchiveRecordRepository archiveRecordRepository, LoggingService loggingService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.archiveRecordRepository = archiveRecordRepository;
        this.loggingService = loggingService;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void archiveSoftDeletedRecords() {
        System.out.println("Archiving..");

        LocalDateTime fourteenDaysAgo = LocalDateTime.now().minusDays(14);

        ArchiveRecord archiveRecord = new ArchiveRecord();

        archiveDeletedUsers(fourteenDaysAgo, archiveRecord);
        archiveDeletedEvents(fourteenDaysAgo, archiveRecord);
    }

    private void archiveDeletedUsers(LocalDateTime dayTimer, ArchiveRecord archiveRecord) {

        List<User> oldUsers = userRepository.findAllInactiveUsers(dayTimer);

        if (oldUsers.isEmpty()) {
            System.out.println("No deleted records of Users, Archiving exited.");
            return;
        }

        oldUsers.forEach(user -> {
            archiveRecord.setRecordType("user");
            archiveRecord.setOriginalRecordId(user.getId());
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(90, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(user.getEmail());
            archiveRecord.setModifiedUser("ArchivingService");
            archiveRecord.setModifiedUserId(10);

            try {
                archiveRecordRepository.save(archiveRecord);
                userRepository.delete(user);

                loggingService.saveLog("archive_logs", "[" + Timestamp.from(Instant.now()) + "]" + " Archiving user: " + user.getEmail() + " successful");
            } catch (Exception e) {
                try {
                    loggingService.saveLog("archive_logs","[" + Timestamp.from(Instant.now()) + "]" + " Archiving old users failed: " + e.getMessage() + " Cause: " + e.getCause());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void archiveDeletedEvents(LocalDateTime dayTimer, ArchiveRecord archiveRecord) {

        List<Event> oldEvents = eventRepository.findAllDeletedEvents(dayTimer);

        if (oldEvents.isEmpty()) {
            System.out.println("No deleted records of Events, Archiving exited.");
            return;
        }

        oldEvents.forEach(event -> {
            archiveRecord.setRecordType("event");
            archiveRecord.setOriginalRecordId(event.getId());
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(event.getTitle());
            archiveRecord.setModifiedUser("ArchivingService");
            archiveRecord.setModifiedUserId(10);

            try {
                archiveRecordRepository.save(archiveRecord);
                eventRepository.delete(event);

                loggingService.saveLog("archive_logs","[" + Timestamp.from(Instant.now()) + "]" + " Archiving event: " + event.getTitle() + " successful");
            } catch (Exception e) {
                try {
                    loggingService.saveLog("archive_logs","[" + Timestamp.from(Instant.now()) + "]" + " Archiving old events failed: " + e.getMessage() + " Cause: " + e.getCause());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
