package com.calzyr.dto.authorization;

import com.calzyr.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@Entity
public class RolePermissionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id")
    private int RolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDTO RoleId;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionDTO PermissionId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
