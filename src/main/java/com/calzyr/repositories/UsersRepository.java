package com.calzyr.repositories;

import com.calzyr.dto.UsersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository extends CrudRepository<UsersModel, Integer> {

    @Query("SELECT u FROM UsersModel u WHERE u.DeletedAt IS NULL")
    Iterable<UsersModel> findAllActiveUsers();

    @Query("SELECT u.DeletedAt, u.Email FROM UsersModel u WHERE u.DeletedAt IS NOT NULL")
    List<UsersModel> findAllInactiveUsers(LocalDateTime dateTime);
}
