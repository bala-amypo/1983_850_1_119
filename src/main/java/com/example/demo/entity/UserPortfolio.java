package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class UserPortfolio {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String portfolioName;
    private Boolean active = true;
    private Timestamp createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() { return id; }
}
