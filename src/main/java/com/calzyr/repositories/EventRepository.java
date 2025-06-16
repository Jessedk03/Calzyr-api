package com.calzyr.repositories;

import com.calzyr.entity.event.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM Event e WHERE e.DeletedAt IS NULL")
    Iterable<Event> findAllEvents();

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM Event e WHERE e.DeletedAt IS NULL AND e.UserId.id = :id")
    Iterable<Event> findByUserId(Integer id);

    @Query("SELECT e FROM Event e WHERE e.DeletedAt IS NOT NULL AND e.DeletedAt < :date")
    List<Event> findAllDeletedEvents(LocalDateTime date);
}
