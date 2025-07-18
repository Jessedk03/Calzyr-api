package com.calzyr.dtos.subscription;

import com.calzyr.entities.subscription.Subscription;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SubscriptionResponseDTO {

    private String subscriptionName;
    private Timestamp expiresAt;

    public SubscriptionResponseDTO(Subscription subscription) {
        this.subscriptionName = subscription.getSubscriptionName();
        this.expiresAt = subscription.getExpiresAt();
    }
}
