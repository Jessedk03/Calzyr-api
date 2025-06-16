package com.calzyr.entity.authentication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "issued_at")
    private Timestamp issuedAt;

    @Column(name = "refreshed_at")
    private Timestamp refreshAt;

    @Column(name = "expired")
    private Boolean expired = false;
}
