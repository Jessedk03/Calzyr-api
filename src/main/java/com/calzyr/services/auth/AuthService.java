package com.calzyr.services.auth;

import com.calzyr.dto.authentication.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
