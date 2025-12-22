package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RiskAnalysisService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RiskAnalysisServiceImpl implements RiskAnalysisService {

    private final PortfolioHoldingRepository holdingRepo;
    private final RiskThresholdRepository thresholdRepo;
    private final RiskAnalysisResultRepository analysisRepo;
    private final UserPortfolioRepository portfolioRepo;

    public RiskAnalysisServiceImpl(PortfolioHoldingRepository holdingRepo,
                                   RiskThresholdRepository thresholdRepo,
                                   RiskAnalysisResultRepository analysisRepo,
                                   UserPortfolioRepository portfolioRepo) {
        this.holdingRepo = holdingRepo;
        this.thresholdRepo = thresholdRepo;
        this.analysisRepo = analysisRepo;
        this.portfolioRepo = portfolioRepo;
    }

    public RiskAnalysisResult analyze(Long portfolioId) {
        UserPortfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        List<PortfolioHolding> holdings = holdingRepo.findByPortfolioId(portfolioId);
        double total = holdings.stream()
                .mapToDouble(h -> h.getMarketValue().doubleValue())
                .sum();

        double highest = holdings.stream()
                .mapToDouble(h -> (h.getMarketValue().doubleValue() / total) * 100)
                .max()
                .orElse(0);

        RiskThreshold threshold = thresholdRepo.findByPortfolioId(portfolioId);
        boolean highRisk = threshold != null && highest > threshold.getMaxSingleStockPercentage();

        RiskAnalysisResult result = new RiskAnalysisResult(
                portfolio, LocalDateTime.now(), highest, highRisk
        );

        return analysisRepo.save(result);
    }

    public List<RiskAnalysisResult> getAnalyses(Long portfolioId) {
        return analysisRepo.findByPortfolioId(portfolioId);
    }
}
