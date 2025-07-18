package com.calzyr.entities.subscription;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_id")
    private Integer Id;

    @Column(name = "subscription_name")
    private String SubscriptionName;

    @Column(name = "subscription_description")
    private String SubscriptionDescription;

    @Column(name = "base_price_monthly")
    private float BasePriceMonthly;

    @Column(name = "base_price_yearly")
    private float BasePriceYearly;

    @Column(name = "expires_at")
    private Timestamp ExpiresAt;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;

}
