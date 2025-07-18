package com.calzyr.dtos.user;


import com.calzyr.dtos.company.CompanyDetailsDTO;
import com.calzyr.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailsResponseDTO {
    private int id;
    private String username;
    private String email;
    private boolean multiTenant;
    private List<CompanyDetailsDTO> companies;

    public UserDetailsResponseDTO(User user, List<CompanyDetailsDTO> companies) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.multiTenant = user.isMultiTenant();
        this.companies = companies;
    }
}

