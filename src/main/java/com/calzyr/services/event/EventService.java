package com.calzyr.services.event;

import com.calzyr.dtos.event.EventResponseDTO;
import com.calzyr.entities.event.Event;
import com.calzyr.entities.user.User;
import com.calzyr.exceptions.NotFoundException;
import com.calzyr.repositories.EventRepository;
import com.calzyr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

//    Get all events
    public Iterable<EventResponseDTO> getAllEvents() {
        Iterable<Event> events = eventRepository.findAllEvents();
        List<EventResponseDTO> dtoList = new ArrayList<>();
        for (Event event : events) {
            dtoList.add(new EventResponseDTO(event));
        }

        return dtoList;
    }

//    Get event by id
    public EventResponseDTO getEventById(Integer id) {
        return eventRepository.findById(id)
                .map(EventResponseDTO::new)
                .orElseThrow(() -> new NotFoundException("Event not found."));
    }

//    Create event
    public EventResponseDTO createEvent(Event newEvent) {
        User user = userRepository.findById(newEvent.getUserId().getId())
                .orElseThrow(() -> new NotFoundException("Event not found"));

        Event event = new Event();
        event.setTitle(newEvent.getTitle());
        event.setDescription(newEvent.getDescription());
        event.setStartTime(newEvent.getStartTime());
        event.setEndTime(newEvent.getEndTime());
        event.setLocation(newEvent.getLocation());
        event.setUserId(user);
        event.setCreatedAt(Timestamp.from(Instant.now()));
        event.setAllDay(newEvent.getAllDay());

        Event savedEvent = eventRepository.save(event);
        return new EventResponseDTO(savedEvent);
    }

//    Update event
    public EventResponseDTO updateEvent(Integer id, Event oldEventData) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));

        event.setTitle(oldEventData.getTitle());
        event.setDescription(oldEventData.getDescription());
        event.setStartTime(oldEventData.getStartTime());
        event.setEndTime(oldEventData.getEndTime());
        event.setLocation(oldEventData.getLocation());
        event.setUserId(oldEventData.getUserId());
        event.setUpdatedAt(Timestamp.from(Instant.now()));
        event.setAllDay(oldEventData.getAllDay());

        Event updatedEvent = eventRepository.save(event);
        return new EventResponseDTO(updatedEvent);
    }

//    Delete event
    public void deleteEvent(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));

        event.setDeletedAt(Timestamp.from(Instant.now()));
        eventRepository.save(event);
    }
}
