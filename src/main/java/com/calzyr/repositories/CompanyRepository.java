package com.calzyr.repositories;

import com.calzyr.entities.company.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository {

    @Query("SELECT c FROM Company WHERE c.CompanyId = :companyId AND c.DeletedAt IS NULL")
    Optional<Company> findById(@Param("companyId") Integer companyId);

}
