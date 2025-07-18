package com.calzyr.repositories;

import com.calzyr.entities.company.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.Id = :companyId AND c.DeletedAt IS NULL")
    Optional<Company> findById(@Param("companyId") Integer companyId);

    @Query("SELECT c FROM Company c WHERE c.Id IN :companyIds AND c.DeletedAt IS NULL")
    List<Company> findCompaniesId(@Param("companyIds") List<Integer> companyIds);

}
