package com.calzyr.repositories;

import com.calzyr.dto.user.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends CrudRepository<UserDTO, Integer> {

    @Query("SELECT u FROM UserDTO u WHERE u.DeletedAt IS NULL")
    Iterable<UserDTO> findAllActiveUsers();

    @Query("SELECT u FROM UserDTO u WHERE u.DeletedAt IS NOT NULL AND u.DeletedAt < :date")
    List<UserDTO> findAllInactiveUsers(LocalDateTime date);
}
