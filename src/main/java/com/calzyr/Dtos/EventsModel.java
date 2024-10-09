package com.calzyr.Dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Entity
public class EventsModel {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String Title;
    private String Description;
    private Timestamp StartTime;
    private Timestamp EndTime;
    private String Location;
    private Integer UserId; // ForeignKey User_ID
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;
    private Timestamp DeletedAt;

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setStartTime(Timestamp startTime) {
        this.StartTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.EndTime = endTime;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public void setUserId(Integer userId) {
        this.UserId = userId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.CreatedAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        DeletedAt = deletedAt;
    }
}
