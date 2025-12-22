package com.example.demo.service;

import com.example.demo.model.RiskAnalysisResult;
import java.util.List;

public interface RiskAnalysisService {
    RiskAnalysisResult analyze(Long portfolioId);
    List<RiskAnalysisResult> getAnalyses(Long portfolioId);
}
