package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "risk_thresholds")
public class RiskThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserPortfolio portfolio;

    private Double maxSingleStockPercentage;
    private Double maxOverallVolatility;

    public RiskThreshold() {}

    public RiskThreshold(UserPortfolio portfolio,
                         Double maxSingleStockPercentage,
                         Double maxOverallVolatility) {
        this.portfolio = portfolio;
        this.maxSingleStockPercentage = maxSingleStockPercentage;
        this.maxOverallVolatility = maxOverallVolatility;
    }

    public Long getId() { return id; }
    public UserPortfolio getPortfolio() { return portfolio; }
    public void setPortfolio(UserPortfolio portfolio) { this.portfolio = portfolio; }

    public Double getMaxSingleStockPercentage() { return maxSingleStockPercentage; }
    public void setMaxSingleStockPercentage(Double value) { this.maxSingleStockPercentage = value; }

    public Double getMaxOverallVolatility() { return maxOverallVolatility; }
    public void setMaxOverallVolatility(Double value) { this.maxOverallVolatility = value; }
}