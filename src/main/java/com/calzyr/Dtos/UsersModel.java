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

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public void setUpdatedAt(Timestamp UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    public void setDeletedAt(Timestamp DeletedAt) {
        this.DeletedAt = DeletedAt;
    }


}
