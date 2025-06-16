package com.calzyr.entity.authorization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id")
    private int RolePermissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role RoleId;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission PermissionId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
