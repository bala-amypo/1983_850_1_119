// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.sql.Timestamp;

// @Entity
// @Table(name = "risk_analysis_results")
// public class RiskAnalysisResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "portfolio_id", nullable = false)
//     private UserPortfolio portfolio;

//     @Column(name = "analysis_date")
//     private Timestamp analysisDate;

//     @Column(name = "highest_stock_percentage")
//     private Double highestStockPercentage;

//     @Column(name = "is_high_risk")
//     private boolean highRisk;   // 🔴 primitive boolean

//     public RiskAnalysisResult() {
//     }

//     public RiskAnalysisResult(
//             UserPortfolio portfolio,
//             Timestamp analysisDate,
//             Double highestStockPercentage,
//             boolean highRisk) {
//         this.portfolio = portfolio;
//         this.analysisDate = analysisDate;
//         this.highestStockPercentage = highestStockPercentage;
//         this.highRisk = highRisk;
//     }

//     // ---------- getters & setters ----------

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public UserPortfolio getPortfolio() {
//         return portfolio;
//     }

//     public void setPortfolio(UserPortfolio portfolio) {
//         this.portfolio = portfolio;
//     }

//     public Timestamp getAnalysisDate() {
//         return analysisDate;
//     }

//     public void setAnalysisDate(Timestamp analysisDate) {
//         this.analysisDate = analysisDate;
//     }

//     public Double getHighestStockPercentage() {
//         return highestStockPercentage;
//     }

//     public void setHighestStockPercentage(Double highestStockPercentage) {
//         this.highestStockPercentage = highestStockPercentage;
//     }

//     // 🔥 REQUIRED BY TEST CASE
//     public boolean isHighRisk() {
//         return highRisk;
//     }

//     public void setHighRisk(boolean highRisk) {
//         this.highRisk = highRisk;
//     }
// }


package com.example.demo.model;

import java.sql.Timestamp;

public class RiskAnalysisResult {
    private Double highestStockPercentage;
    private boolean highRisk;
    private Timestamp analysisDate;

    public Double getHighestStockPercentage() {
        return highestStockPercentage;
    }

    public void setHighestStockPercentage(Double highestStockPercentage) {
        this.highestStockPercentage = highestStockPercentage;
    }

    public boolean isHighRisk() {
        return highRisk;
    }

    public void setHighRisk(boolean highRisk) {
        this.highRisk = highRisk;
    }

    public Timestamp getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(Timestamp analysisDate) {
        this.analysisDate = analysisDate;
    }
}