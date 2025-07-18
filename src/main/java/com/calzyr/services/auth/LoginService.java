package com.calzyr.services.auth;

import com.calzyr.dtos.authentication.JwtAuthResponseDTO;
import com.calzyr.dtos.authentication.LoginDTO;
import com.calzyr.dtos.authentication.LoginResponseDTO;
import com.calzyr.dtos.company.CompanyDetailsDTO;
import com.calzyr.dtos.user.UserDetailsResponseDTO;
import com.calzyr.entities.company.Company;
import com.calzyr.entities.user.User;
import com.calzyr.repositories.CompanyRepository;
import com.calzyr.repositories.CompanyUserRepository;
import com.calzyr.repositories.UserRepository;
import com.calzyr.security.JwtTokenProvider;
import com.calzyr.services.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CookieService cookieService;

    private User getUserOrThrow(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public LoginResponseDTO login(LoginDTO loginDto, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();

        // Genereer token
        String token = authService.login(loginDto);

        // Haal de gebruiker op
        User user = getUserOrThrow(loginDto.getUsernameOrEmail());

        // Zet cookie
        cookieService.setAccessToken(response, token);


//        List<Integer> companyOfUser = companyUserRepository.findCompanyOfUser(user.getId());
//        List<Company> companiesList = companyRepository.findCompaniesId(companyOfUser);
//        List<CompanyDetailsDTO> companies = new CompanyDetailsDTO(companiesList);
//        UserDetailsResponseDTO userResponse = new UserDetailsResponseDTO(user, companies);
        UserDetailsResponseDTO userResponse = userService.userDetails(user.getId());

        LoginResponseDTO loginResponse = new LoginResponseDTO(token, "Bearer", userResponse);

        long elapsedTime = System.currentTimeMillis() - startTime;
        long remainingTime = 1000 - elapsedTime;

        if (remainingTime > 0) {
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException ignored) {
            }
        }

        return loginResponse;
    }

    public JwtAuthResponseDTO refreshToken(Map<String, String> request) throws IOException {
        String refreshToken = request.get("refreshToken");
        return jwtTokenProvider.refreshToken(refreshToken);
    }
}
