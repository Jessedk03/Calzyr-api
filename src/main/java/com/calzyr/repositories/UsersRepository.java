package com.calzyr.repositories;

import com.calzyr.dto.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersModel, Integer> {

    @Query("SELECT u FROM UsersModel u WHERE u.DeletedAt IS NULL")
    Iterable<UsersModel> findAllActiveUsers();
}
