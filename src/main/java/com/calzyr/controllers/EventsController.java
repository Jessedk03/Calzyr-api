package com.calzyr.controllers;

import com.calzyr.dto.EventsModel;
import com.calzyr.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping
    public Iterable<EventsModel> getAllEvents() {
        return eventsRepository.findAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<EventsModel> getEventById(@PathVariable Integer id) {
        return eventsRepository.findById(id);
    }

    @PostMapping
    public EventsModel createEvent(@RequestBody EventsModel event) {
        return eventsRepository.save(event);
    }

    @PutMapping("/{id}/update")
    public EventsModel updateEvent(@PathVariable Integer id, @RequestBody EventsModel eventDetails) {
        EventsModel event = eventsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + id));

        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setStartTime(eventDetails.getStartTime());
        event.setEndTime(eventDetails.getEndTime());
        event.setLocation(eventDetails.getLocation());
        event.setUserId(eventDetails.getUserId());
        event.setUpdatedAt(Timestamp.from(Instant.now()));
        event.setAllDay(eventDetails.getAllDay());

        return eventsRepository.save(event);
    }

    @DeleteMapping("/{id}/delete")
    public EventsModel deleteEvent(@PathVariable Integer id, @RequestBody EventsModel eventDelete) {
        EventsModel event = eventsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + id));

        event.setDeletedAt(Timestamp.from(Instant.now()));

        return eventsRepository.save(event);
    }
}
