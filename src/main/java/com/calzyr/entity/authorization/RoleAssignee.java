package com.calzyr.entity.authorization;

import com.calzyr.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "role_assignees")
public class RoleAssignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_assignee_id")
    private int RoleAssigneeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User UserId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role RoleId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
