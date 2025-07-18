package com.calzyr.dtos.user;

import com.calzyr.entities.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private boolean multiTenant;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.multiTenant = user.isMultiTenant();
    }
}
