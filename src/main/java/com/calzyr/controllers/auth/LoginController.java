package com.calzyr.controllers.auth;

import com.calzyr.dto.authentication.JwtAuthResponse;
import com.calzyr.dto.authentication.LoginDTO;
import com.calzyr.repositories.UserRepository;
import com.calzyr.security.JwtTokenProvider;
import com.calzyr.services.auth.AuthService;
import com.calzyr.services.auth.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        String token = authService.login(loginDto);

        // Set Cookie.
        cookieService.setAccessToken(response, token);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        long elapsedTime = System.currentTimeMillis() - startTime;
        long remainingTime = 1000 - elapsedTime;

        if (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException ignored) {
            }
        }

        return new ResponseEntity<>(jwtAuthResponse, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestBody Map<String, String> request) throws IOException {
        String refreshToken = request.get("refreshToken");
        JwtAuthResponse jwtAuthResponse = jwtTokenProvider.refreshToken(refreshToken);
        if (jwtAuthResponse != null) {
            return ResponseEntity.ok(jwtAuthResponse);
        }
        return ResponseEntity.status(401).build();
    }
}
