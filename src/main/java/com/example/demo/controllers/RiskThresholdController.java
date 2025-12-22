package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class RiskThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thresholdName;
    private Double maxSingleStockPercentage;
    private Double maxSectorPercentage;
    private Boolean active = true;

    public Long getId() { return id; }
    public String getThresholdName() { return thresholdName; }
    public Double getMaxSingleStockPercentage() { return maxSingleStockPercentage; }
    public Double getMaxSectorPercentage() { return maxSectorPercentage; }
    public Boolean getActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setThresholdName(String thresholdName) { this.thresholdName = thresholdName; }
    public void setMaxSingleStockPercentage(Double v) { this.maxSingleStockPercentage = v; }
    public void setMaxSectorPercentage(Double v) { this.maxSectorPercentage = v; }
    public void setActive(Boolean active) { this.active = active; }
}