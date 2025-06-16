package com.calzyr.entity.event;

import com.calzyr.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Integer Id;

    @Column(name = "title")
    private String Title;

    @Column(name = "description")
    private String Description;

    @Column(name = "start_time")
    private Timestamp StartTime;

    @Column(name = "end_time")
    private Timestamp EndTime;

    @Column(name = "location")
    private String Location;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User UserId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;

    @Column(name = "all_day")
    private Boolean AllDay;

}
