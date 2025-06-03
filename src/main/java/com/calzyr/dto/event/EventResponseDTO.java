package com.calzyr.dto.event;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventResponseDTO {
    private int id;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean allDay;
    private int userId;

    public EventResponseDTO(EventDTO event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.allDay = event.getAllDay();
        this.userId = event.getUserId().getId();
    }
}
