package com.example.demo.service;

import com.example.demo.entity.RiskAnalysisResult;

import java.util.List;

public interface RiskAnalysisService {

    RiskAnalysisResult analyzePortfolio(Long portfolioId);

    RiskAnalysisResult getAnalysisById(Long id);

    List<RiskAnalysisResult> getAnalysesForPortfolio(Long portfolioId);
}
