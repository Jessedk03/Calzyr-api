package com.calzyr.dtos.authentication;

import com.calzyr.dtos.company.CompanyResponseDTO;
import com.calzyr.dtos.user.UserDetailsResponseDTO;
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
    private UserDetailsResponseDTO userDetailsResponseDTO;
//    private UserResponseDTO userResponseDTO;
//    private CompanyResponseDTO companyResponseDTO;
}
