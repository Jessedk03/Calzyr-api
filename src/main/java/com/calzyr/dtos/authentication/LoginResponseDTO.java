package com.calzyr.dtos.authentication;

import com.calzyr.dtos.user.UserResponseDTO;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    private String tokenType;
    private UserResponseDTO userResponseDTO;
}
