package com.calzyr.dtos.company;

import com.calzyr.entities.company.Company;
import com.calzyr.entities.company.CompanySubscription;
import com.calzyr.entities.subscription.Subscription;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanySubscriptionResponseDTO {

    private Integer companyId;
    private Integer subscriptionId;
    private Integer userAmount;
    private float totalPrice;

    public CompanySubscriptionResponseDTO(CompanySubscription companySubscription, Company company, Subscription subscription) {
        this.companyId = company.getId();
        this.subscriptionId = subscription.getId();
        this.userAmount = companySubscription.getUserAmount();
        totalPrice = companySubscription.getTotalPrice();
    }
}
