package com.calzyr.services.auth;

import com.calzyr.dto.authentication.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
