package com.calzyr.controllers;

import com.calzyr.dtos.event.EventResponseDTO;
import com.calzyr.entities.event.Event;
import com.calzyr.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Iterable<?> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable Integer id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody Event newEvent) {
        return eventService.createEvent(newEvent);
    }

    @PatchMapping("/{id}/update")
    public EventResponseDTO updateEvent(@PathVariable Integer id, @RequestBody Event oldEventData) {
        return eventService.updateEvent(id, oldEventData);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Successfully marked event for deletion. #" + id);
    }
}
