package com.calzyr.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;

    public UserResponseDTO(UserDTO user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
