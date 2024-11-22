package com.calzyr.repositories;

import com.calzyr.dto.events.EventDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<EventDTO, Integer> {

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM EventDTO e WHERE e.DeletedAt IS NULL")
    Iterable<EventDTO> findAllEvents();

    @Query("SELECT e FROM EventDTO e WHERE e.DeletedAt IS NOT NULL AND e.DeletedAt < :date")
    List<EventDTO> findAllDeletedEvents(LocalDateTime date);
}
