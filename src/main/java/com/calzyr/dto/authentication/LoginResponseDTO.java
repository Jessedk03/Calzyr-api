package com.calzyr.dto.authentication;

import com.calzyr.dto.user.UserResponseDTO;
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
