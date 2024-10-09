package com.calzyr.Dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
public class UsersModel {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String Username;
    private String Email;
    private String Password;
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;
    private Timestamp DeletedAt;

    public Integer getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public Timestamp getUpdatedAt() {
        return UpdatedAt;
    }

    public Timestamp getDeletedAt() {
        return DeletedAt;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.CreatedAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.DeletedAt = deletedAt;
    }

}
