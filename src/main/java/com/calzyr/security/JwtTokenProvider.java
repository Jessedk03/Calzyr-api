package com.calzyr.security;

import com.calzyr.dto.authentication.JwtAuthResponse;
import com.calzyr.dto.authentication.RefreshTokenDTO;
import com.calzyr.dto.user.UserDTO;
import com.calzyr.repositories.UserRepository;
import com.calzyr.repositories.authentication.RefreshTokenRepository;
import com.calzyr.services.LoggingService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    @Value("${app.jwt-refresh-expiration-milliseconds}")
    private long jwtRefreshExpirationDate;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoggingService loggingService;

    public JwtAuthResponse refreshToken(String refreshToken) throws IOException {
        if (!validateToken(refreshToken)) {
            return null;
        }

        String username = getUsername(refreshToken);

        Optional<RefreshTokenDTO> optionalToken = refreshTokenRepository.findByRefreshToken(refreshToken);

        if (optionalToken.isEmpty()) {
            return null;
        }

        RefreshTokenDTO storedToken = optionalToken.get();

        if (Boolean.TRUE.equals(storedToken.getExpired())) {
            return null;
        }

        Integer userId = storedToken.getUserId();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, List.of());
        String newAccessToken = generateToken(auth, userId, false);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(newAccessToken);

        return response;

    }

    public String generateToken(Authentication authentication, int userId) throws IOException {
        return generateToken(authentication, userId, true);
    }

    public String generateToken(Authentication authentication, int userId, Boolean createdRefreshToken) throws IOException {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String accessToken = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(key())
                .compact();

        if (createdRefreshToken) {
            generateRefreshToken(authentication, accessToken, userId);
        }

        return accessToken;
    }

    public void generateRefreshToken(Authentication authentication, String accessToken, int userId) throws IOException {
        try {
            List<RefreshTokenDTO> existingTokens = refreshTokenRepository.findAllByUserIdAndExpired(userId);

            for (RefreshTokenDTO refreshToken : existingTokens) {
                refreshToken.setExpired(true);
                refreshTokenRepository.save(refreshToken);
            }
        } catch (Exception e) {
            loggingService.saveLog("jwt_token_provider", "no prior access tokens for user:" + userId);
        }


        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtRefreshExpirationDate);

        String refreshToken = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key())
                .compact();
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
        refreshTokenDTO.setUserId(userId);
        refreshTokenDTO.setAccessToken(accessToken);
        refreshTokenDTO.setRefreshToken(refreshToken);
        refreshTokenDTO.setIssuedAt(Timestamp.from(Instant.now()));
        refreshTokenDTO.setRefreshAt(Timestamp.from(Instant.now()));
        refreshTokenDTO.setExpired(false);

        refreshTokenRepository.save(refreshTokenDTO);
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) throws IOException {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            loggingService.saveLog("jwt_token_provider", e.getMessage());
            return false;
        }
    }
}
