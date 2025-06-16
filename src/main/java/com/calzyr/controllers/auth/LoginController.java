package com.calzyr.controllers.auth;

import com.calzyr.dto.authentication.JwtAuthResponseDTO;
import com.calzyr.dto.authentication.LoginDTO;
import com.calzyr.dto.authentication.LoginResponseDTO;
import com.calzyr.entity.user.User;
import com.calzyr.dto.user.UserResponseDTO;
import com.calzyr.repositories.UserRepository;
import com.calzyr.security.JwtTokenProvider;
import com.calzyr.services.auth.AuthService;
import com.calzyr.services.auth.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CookieService cookieService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDto, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();

        // Genereer token
        String token = authService.login(loginDto);

        // Haal de gebruiker op
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(
                loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()
        );

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();

        // Zet cookie
        cookieService.setAccessToken(response, token);

        // TODO: Voeg hier de Subscription ook aan toe.
        UserResponseDTO userResponse = new UserResponseDTO(user);

        LoginResponseDTO loginResponse = new LoginResponseDTO(token, "Bearer", userResponse);

        long elapsedTime = System.currentTimeMillis() - startTime;
        long remainingTime = 1000 - elapsedTime;

        if (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException ignored) {
            }
        }

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponseDTO> refreshToken(@RequestBody Map<String, String> request) throws IOException {
        String refreshToken = request.get("refreshToken");
        JwtAuthResponseDTO jwtAuthResponseDTO = jwtTokenProvider.refreshToken(refreshToken);
        if (jwtAuthResponseDTO != null) {
            return ResponseEntity.ok(jwtAuthResponseDTO);
        }
        return ResponseEntity.status(401).build();
    }
}
