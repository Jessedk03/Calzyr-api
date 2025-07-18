package com.calzyr.repositories;

import com.calzyr.entities.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query("SELECT s FROM Subscription s WHERE s.DeletedAt IS NULL")
    Iterable<Subscription> findAllSubscriptions();

    @Query("SELECT s FROM Subscription s WHERE s.Id IN :subscriptionIds AND s.DeletedAt IS NULL")
    List<Subscription> findAllSubscriptionsById(@Param("subscriptionIds") List<Integer> subscriptionIds);

    @Query("SELECT s FROM Subscription s WHERE s.Id = :subscriptionId AND s.DeletedAt IS NULL")
    Optional<Subscription> findById(@Param("subscriptionId") Integer subscriptionId);

}
