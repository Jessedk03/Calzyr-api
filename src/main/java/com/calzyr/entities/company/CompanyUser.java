package com.calzyr.entities.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "company_users")
public class CompanyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_user_id")
    private Integer Id;

    @Column(name = "user_id")
    private Integer UserId;

    @Column(name = "company_id")
    private Integer CompanyId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
