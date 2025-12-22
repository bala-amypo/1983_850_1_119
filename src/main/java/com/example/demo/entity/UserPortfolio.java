package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class UserPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String portfolioName;
    private Boolean active = true;
    private Timestamp createdAt;

    @PrePersist
    void created() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getPortfolioName() { return portfolioName; }
    public Boolean getActive() { return active; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setPortfolioName(String portfolioName) { this.portfolioName = portfolioName; }
    public void setActive(Boolean active) { this.active = active; }
}