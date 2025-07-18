package com.calzyr.entities.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "company_subscriptions")
public class CompanySubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_subscription_id")
    private Integer Id;

    @Column(name = "company_id")
    private Integer CompanyId;

    @Column(name = "subscription_id")
    private Integer SubscriptionId;

    @Column(name = "user_amount")
    private Integer UserAmount;

    @Column(name = "price_per_user")
    private float PricePerUser;

    @Column(name = "total_price")
    private float TotalPrice;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
