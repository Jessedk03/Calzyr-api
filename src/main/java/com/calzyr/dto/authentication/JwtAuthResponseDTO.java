package com.calzyr.dto.authentication;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";
}
