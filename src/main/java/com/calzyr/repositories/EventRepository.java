package com.calzyr.repositories;

import com.calzyr.dto.event.EventDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<EventDTO, Integer> {

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM EventDTO e WHERE e.DeletedAt IS NULL")
    Iterable<EventDTO> findAllEvents();

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM EventDTO e WHERE e.DeletedAt IS NULL AND e.UserId.id = :id")
    Iterable<EventDTO> findByUserId(Integer id);

    @Query("SELECT e FROM EventDTO e WHERE e.DeletedAt IS NOT NULL AND e.DeletedAt < :date")
    List<EventDTO> findAllDeletedEvents(LocalDateTime date);
}
