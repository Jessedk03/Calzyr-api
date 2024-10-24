package com.calzyr.repositories;

import com.calzyr.dto.EventsModel;
import com.calzyr.dto.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<EventsModel, Integer> {

    @Query("SELECT e FROM EventsModel e WHERE e.DeletedAt IS NULL")
    Iterable<EventsModel> findAllEvents();
}
