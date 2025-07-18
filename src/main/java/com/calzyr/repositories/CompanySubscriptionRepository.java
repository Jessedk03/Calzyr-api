package com.calzyr.repositories;

import com.calzyr.entities.company.Company;
import com.calzyr.entities.company.CompanySubscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CompanySubscriptionRepository extends CrudRepository<CompanySubscription, Integer> {

    @Query("SELECT cs FROM CompanySubscription cs WHERE cs.Id = :companyId AND cs.DeletedAt IS NULL")
    Optional<CompanySubscription> findByCompanyId(@Param("companyId") Integer companyId);

    @Query("SELECT cs.Id FROM CompanySubscription cs WHERE cs.Id IN :companyIds AND cs.DeletedAt IS NULL")
    List<Integer> findSubscriptionOfCompanies(@Param("companyIds") List<Integer> companyIds);

}
