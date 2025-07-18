package com.calzyr.repositories;

import com.calzyr.entities.company.CompanyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyUserRepository extends CrudRepository<CompanyUser, Integer> {

    @Query("SELECT cu.UserId, cu.CompanyId FROM CompanyUser cu WHERE cu.UserId = :userId AND cu.DeletedAt IS NULL")
    Iterable<CompanyUser> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT cu.UserId, cu.CompanyId FROM CompanyUser cu WHERE cu.CompanyId = :companyId AND cu.DeletedAt IS NULL")
    Iterable<CompanyUser> findByCompanyId(@Param("companyId") Integer companyId);

    @Query("SELECT cu.UserId, cu.CompanyId FROM CompanyUser cu WHERE cu.UserId = :userId AND cu.CompanyId = :companyId AND cu.DeletedAt IS NULL")
    Optional<CompanyUser> findUserInCompany(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    @Query("SELECT cu.CompanyId FROM CompanyUser cu WHERE cu.UserId = :userId AND cu.DeletedAt IS NULL")
    List<Integer> findCompanyOfUser(Integer userId);
}
