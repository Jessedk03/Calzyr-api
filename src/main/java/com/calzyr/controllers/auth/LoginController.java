package com.calzyr.controllers.auth;

import com.calzyr.dtos.authentication.JwtAuthResponseDTO;
import com.calzyr.dtos.authentication.LoginDTO;
import com.calzyr.dtos.authentication.LoginResponseDTO;
import com.calzyr.services.auth.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto, HttpServletResponse response) throws IOException {
        LoginResponseDTO loginResponseDTO = loginService.login(loginDto, response);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) throws IOException {
        JwtAuthResponseDTO jwtAuthResponseDTO = loginService.refreshToken(request);
        return ResponseEntity.ok(jwtAuthResponseDTO);
    }
}
