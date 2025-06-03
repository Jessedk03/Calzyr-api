package com.calzyr.controllers;

import com.calzyr.dto.event.EventResponseDTO;
import com.calzyr.dto.event.EventDTO;
import com.calzyr.dto.user.UserDTO;
import com.calzyr.repositories.EventRepository;
import com.calzyr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<EventDTO> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<EventDTO> getEventById(@PathVariable Integer id) {
        return eventRepository.findById(id);
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody EventDTO eventDetails) {

        try {
            UserDTO user = userRepository.findById(eventDetails.getUserId().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            EventDTO event = new EventDTO();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            event.setLocation(eventDetails.getLocation());
            event.setUserId(user);
            event.setCreatedAt(Timestamp.from(Instant.now()));
            event.setAllDay(eventDetails.getAllDay());

            EventDTO savedEvent = eventRepository.save(event);
            return new EventResponseDTO(savedEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed creating an event", e);
        }
    }

    @PatchMapping("/{id}/update")
    public EventResponseDTO updateEvent(@PathVariable Integer id, @RequestBody EventDTO eventDetails) {
        try {
            EventDTO event = eventRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + id));

            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            event.setLocation(eventDetails.getLocation());
            event.setUserId(eventDetails.getUserId());
            event.setUpdatedAt(Timestamp.from(Instant.now()));
            event.setAllDay(eventDetails.getAllDay());

            EventDTO savedEvent = eventRepository.save(event);
            return new EventResponseDTO(savedEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed updating an event" + e);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        try {
            EventDTO event = eventRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + id));

            event.setDeletedAt(Timestamp.from(Instant.now()));
            eventRepository.save(event);
            return ResponseEntity.ok("Event marked as deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed deleting an event" + e);
        }
    }
}
