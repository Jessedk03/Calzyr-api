package com.calzyr.services;

import com.calzyr.dto.ArchiveRecordsModel;
import com.calzyr.dto.EventsModel;
import com.calzyr.dto.UsersModel;
import com.calzyr.repositories.ArchiveRecordsRepository;
import com.calzyr.repositories.EventsRepository;
import com.calzyr.repositories.UsersRepository;
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

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;
    private final ArchiveRecordsRepository archiveRecordsRepository;
    private final LoggingService loggingService;

    public ArchivingService(EventsRepository eventsRepository, UsersRepository usersRepository, ArchiveRecordsRepository archiveRecordsRepository, LoggingService loggingService) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.archiveRecordsRepository = archiveRecordsRepository;
        this.loggingService = loggingService;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void archiveSoftDeletedRecords() {
        System.out.println("Archiving..");

        LocalDateTime fourteenDaysAgo = LocalDateTime.now().minusDays(14);

        ArchiveRecordsModel archiveRecord = new ArchiveRecordsModel();

        archiveDeletedUsers(fourteenDaysAgo, archiveRecord);
        archiveDeletedEvents(fourteenDaysAgo, archiveRecord);
    }

    private void archiveDeletedUsers(LocalDateTime dayTimer, ArchiveRecordsModel archiveRecord) {

        List<UsersModel> oldUsers = usersRepository.findAllInactiveUsers(dayTimer);

        if (oldUsers.isEmpty()) {
            System.out.println("No deleted records of Users, Archiving exited.");
            return;
        }

        oldUsers.forEach(user -> {
            archiveRecord.setRecordType("user");
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(90, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(user.getEmail());
            archiveRecord.setModifiedUser("ArchivingService");
            archiveRecord.setModifiedUserId(10);

            try {
                archiveRecordsRepository.save(archiveRecord);
                usersRepository.delete(user);

                loggingService.archiveLog("[" + Timestamp.from(Instant.now()) + "]" + " Archiving user: " + user.getEmail() + " successful");
            } catch (Exception e) {
                try {
                    loggingService.archiveLog("[" + Timestamp.from(Instant.now()) + "]" + " Archiving old users failed: " + e.getMessage() + " Cause: " + e.getCause());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void archiveDeletedEvents(LocalDateTime dayTimer, ArchiveRecordsModel archiveRecord) {

        List<EventsModel> oldEvents = eventsRepository.findAllDeletedEvents(dayTimer);

        if (oldEvents.isEmpty()) {
            System.out.println("No deleted records of Events, Archiving exited.");
            return;
        }

        oldEvents.forEach(event -> {
            archiveRecord.setRecordType("event");
            archiveRecord.setMovedAt(Timestamp.from(Instant.now()));
            archiveRecord.setPermanentDeletedAt(Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS)));
            archiveRecord.setAdditionalInformation(event.getTitle());
            archiveRecord.setModifiedUser("ArchivingService");
            archiveRecord.setModifiedUserId(10);

            try {
                archiveRecordsRepository.save(archiveRecord);
                eventsRepository.delete(event);

                loggingService.archiveLog("[" + Timestamp.from(Instant.now()) + "]" + " Archiving event: " + event.getTitle() + " successful");
            } catch (Exception e) {
                try {
                    loggingService.archiveLog("[" + Timestamp.from(Instant.now()) + "]" + " Archiving old events failed: " + e.getMessage() + " Cause: " + e.getCause());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
