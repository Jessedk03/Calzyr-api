package com.calzyr.controllers.auth;

import com.calzyr.dto.authentication.JwtAuthResponse;
import com.calzyr.dto.authentication.LoginDto;
import com.calzyr.repositories.UserRepository;
import com.calzyr.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        long startTime = System.currentTimeMillis();
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        long elapsedTime = System.currentTimeMillis() - startTime;
        long remainingTime = 1000 - elapsedTime;

        if (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException ignored) {}
        }

        return new ResponseEntity<>(jwtAuthResponse, HttpStatusCode.valueOf(200));
    }
}
