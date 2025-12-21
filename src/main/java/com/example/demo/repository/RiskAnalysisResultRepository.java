package com.example.demo.repository;

import com.example.demo.entity.RiskAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAnalysisResultRepository extends JpaRepository<RiskAnalysisResult, Long> {

    // Used in RiskAnalysisService
    List<RiskAnalysisResult> findByPortfolioId(Long portfolioId);
}
