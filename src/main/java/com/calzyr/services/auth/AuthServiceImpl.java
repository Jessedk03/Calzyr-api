package com.calzyr.services.auth;

import com.calzyr.dto.authentication.LoginDTO;
import com.calzyr.repositories.UserRepository;
import com.calzyr.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    @Override
    public String login(LoginDTO loginDto) throws IOException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Integer userId = userRepository.getIdByUsernameOrEmail(loginDto.getUsernameOrEmail());

        return jwtTokenProvider.generateToken(authentication, userId);
    }
}
