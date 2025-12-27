package com.example.demo.service.impl;

import com.example.demo.model.PortfolioHolding;
import com.example.demo.model.RiskAnalysisResult;
import com.example.demo.model.RiskThreshold;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.RiskAnalysisResultRepository;
import com.example.demo.service.PortfolioHoldingService;
import com.example.demo.service.RiskAnalysisService;
import com.example.demo.service.RiskThresholdService;
import com.example.demo.service.UserPortfolioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class RiskAnalysisServiceImpl implements RiskAnalysisService {

    private final RiskAnalysisResultRepository riskAnalysisResultRepository;
    private final UserPortfolioService userPortfolioService;
    private final PortfolioHoldingService portfolioHoldingService;
    private final RiskThresholdService riskThresholdService;

    public RiskAnalysisServiceImpl(RiskAnalysisResultRepository riskAnalysisResultRepository,
                                   UserPortfolioService userPortfolioService,
                                   PortfolioHoldingService portfolioHoldingService,
                                   RiskThresholdService riskThresholdService) {
        this.riskAnalysisResultRepository = riskAnalysisResultRepository;
        this.userPortfolioService = userPortfolioService;
        this.portfolioHoldingService = portfolioHoldingService;
        this.riskThresholdService = riskThresholdService;
    }

    @Override
    public RiskAnalysisResult analyzePortfolio(Long portfolioId) {
        UserPortfolio portfolio = userPortfolioService.getPortfolioById(portfolioId);
        List<PortfolioHolding> holdings = portfolioHoldingService.getHoldingsByPortfolio(portfolioId);
        RiskThreshold threshold = riskThresholdService.getThresholdForPortfolio(portfolioId);

        if (holdings.isEmpty()) {
            throw new IllegalStateException("Portfolio has no holdings to analyze");
        }

        // Calculate total portfolio value
        BigDecimal totalValue = BigDecimal.ZERO;
        for (PortfolioHolding h : holdings) {
            totalValue = totalValue.add(h.getMarketValue());
        }

        // Calculate highest single-stock percentage
        double highestPercentage = 0.0;
        if (totalValue.compareTo(BigDecimal.ZERO) > 0) {
            for (PortfolioHolding h : holdings) {
                double pct = h.getMarketValue()
                        .divide(totalValue, 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .doubleValue();
                if (pct > highestPercentage) {
                    highestPercentage = pct;
                }
            }
        }

        // Determine if portfolio is high risk
        boolean isHighRisk = false;
        if (threshold != null) {
            if (highestPercentage > threshold.getMaxSingleStockPercentage()) {
                isHighRisk = true;
            }
        }

        // Create and save analysis result
        RiskAnalysisResult result = new RiskAnalysisResult();
        result.setPortfolio(portfolio);
        result.setAnalysisDate(Timestamp.from(Instant.now()));
        result.setHighestStockPercentage(highestPercentage);
        result.setHighRisk(isHighRisk); // <-- use correct setter

        return riskAnalysisResultRepository.save(result);
    }

    @Override
    public List<RiskAnalysisResult> getAnalysesForPortfolio(Long portfolioId) {
        return riskAnalysisResultRepository.findByPortfolioId(portfolioId);
    }
}