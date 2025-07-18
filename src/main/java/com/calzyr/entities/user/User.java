package com.calzyr.entities.user;

import com.calzyr.entities.authorization.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer Id;

    @Column(name = "username")
    private String Username;

    @Column(name = "email")
    private String Email;

    @Column(name = "password")
    private String Password;

    @Column(name = "is_contact")
    private boolean IsContact;

//    Getting set as true once an existing customer adds a new company to their subscription and this user needs access
//    to that company on the same account
    @Column(name = "multi_tenant")
    private boolean MultiTenant;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_assignees", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

}
