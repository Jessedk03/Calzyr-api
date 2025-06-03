package com.calzyr.dto.authentication;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
