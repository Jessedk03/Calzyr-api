package com.calzyr.repositories;

import com.calzyr.entities.subscription.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository {

    @Query("SELECT s FROM Subscription WHERE s.SubscriptionId = :subscriptionId AND s.DeletedAt IS NULL")
    Optional<Subscription> findById(@Param("subscriptionId") Integer subscriptionId);

}
