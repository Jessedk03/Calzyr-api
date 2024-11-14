package com.calzyr.repositories;

import com.calzyr.dto.EventsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventsRepository extends CrudRepository<EventsModel, Integer> {

    @Query("SELECT e.Title, e.Description, e.StartTime, e.EndTime, e.Location, e.AllDay FROM EventsModel e WHERE e.DeletedAt IS NULL")
    Iterable<EventsModel> findAllEvents();

    @Query("SELECT e.DeletedAt, e.Title FROM EventsModel e WHERE e.DeletedAt IS NOT NULL")
    List<EventsModel> findAllDeletedEvents(LocalDateTime dateTime);
}
