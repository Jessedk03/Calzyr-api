package com.calzyr.dtos.company;

import com.calzyr.entities.company.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO {

    private String companyName;

    public CompanyResponseDTO(Company company){
        this.companyName = company.getCompanyName();

    }

}
