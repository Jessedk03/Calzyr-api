package com.calzyr.repositories.authentication;

import com.calzyr.dto.authentication.RefreshTokenDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenDTO, Integer> {

    @Query("SELECT rt FROM RefreshTokenDTO rt WHERE rt.expired IS FALSE AND rt.refreshToken = :refresh_token")
    Optional<RefreshTokenDTO> findByRefreshToken(@Param("refresh_token") String refresh_token);

    @Query("SELECT rt FROM RefreshTokenDTO rt WHERE rt.userId = :user_id AND rt.expired IS FALSE")
    Iterable<RefreshTokenDTO> findByUserId(@Param("user_id") int user_id);

    @Query("SELECT rt FROM RefreshTokenDTO rt WHERE rt.userId = :user_id AND rt.expired IS TRUE")
    List<RefreshTokenDTO> findAllByUserIdAndExpired(@Param("user_id") int user_id);

}
