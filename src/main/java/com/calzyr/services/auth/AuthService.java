package com.calzyr.services.auth;

import com.calzyr.dtos.authentication.LoginDTO;

import java.io.IOException;

public interface AuthService {
    String login(LoginDTO loginDto) throws IOException;
}
