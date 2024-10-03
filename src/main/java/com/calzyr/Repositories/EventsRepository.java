package com.calzyr.Repositories;

import com.calzyr.Dtos.EventsModel;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<EventsModel, Integer> {
}
