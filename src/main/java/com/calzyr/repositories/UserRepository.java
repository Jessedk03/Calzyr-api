package com.calzyr.repositories;

import com.calzyr.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.DeletedAt IS NULL")
    Iterable<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.DeletedAt IS NULL AND (u.Username = :username OR u.Email = :email)")
    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT u.Id FROM User u WHERE u.DeletedAt IS NULL AND (u.Username = :input OR u.Email = :input)")
    Integer getIdByUsernameOrEmail(@Param("input") String usernameOrEmail);

    @Query("SELECT u FROM User u WHERE u.DeletedAt IS NOT NULL AND u.DeletedAt < :date")
    List<User> findAllInactiveUsers(LocalDateTime date);
}
