package com.calzyr.dtos.company;

import com.calzyr.dtos.subscription.SubscriptionResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDetailsDTO {

    private List<CompanyResponseDTO> company;
    private List<SubscriptionResponseDTO> subscription;

    public CompanyDetailsDTO(List<CompanyResponseDTO> company, List<SubscriptionResponseDTO> subscription) {
        this.company = company;
        this.subscription = subscription;
    }
}
