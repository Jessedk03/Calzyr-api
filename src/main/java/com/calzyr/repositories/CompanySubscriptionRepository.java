package com.calzyr.repositories;

import com.calzyr.entities.company.CompanySubscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanySubscriptionRepository {

    @Query("SELECT cs FROM CompanySubscription WHERE cs.CompanyId = :companyId AND cs.DeletedAt IS NULL")
    Optional<CompanySubscription> findByCompanyId(@Param("companyId") Integer companyId);

}
