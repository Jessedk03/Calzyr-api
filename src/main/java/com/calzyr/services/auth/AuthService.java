package com.calzyr.services.auth;

import com.calzyr.dto.authentication.LoginDTO;

import java.io.IOException;

public interface AuthService {
    String login(LoginDTO loginDto) throws IOException;
}
