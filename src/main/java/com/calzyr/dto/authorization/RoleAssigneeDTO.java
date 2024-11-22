package com.calzyr.dto.authorization;

import com.calzyr.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@Entity
public class RoleAssigneeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_assignee_id")
    private int RoleAssigneeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO UserId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDTO RoleId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
