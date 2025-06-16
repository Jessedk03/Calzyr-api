package com.calzyr.entities.authorization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int Id;

    @Column(name = "role_name")
    private String Name;

    @Column(name = "role_description")
    private String RoleDescription;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Timestamp CreatedAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private Timestamp DeletedAt;
}
