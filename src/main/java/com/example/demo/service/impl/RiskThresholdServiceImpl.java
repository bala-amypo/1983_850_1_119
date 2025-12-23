package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.RiskThreshold;
import com.example.demo.entity.UserPortfolio;
import com.example.demo.repository.RiskThresholdRepository;
import com.example.demo.service.RiskThresholdService;
import com.example.demo.service.UserPortfolioService;
import org.springframework.stereotype.Service;

@Service
public class RiskThresholdServiceImpl implements RiskThresholdService {

    private final RiskThresholdRepository riskThresholdRepository;
    private final UserPortfolioService userPortfolioService;

    public RiskThresholdServiceImpl(RiskThresholdRepository riskThresholdRepository,
            UserPortfolioService userPortfolioService) {
        this.riskThresholdRepository = riskThresholdRepository;
        this.userPortfolioService = userPortfolioService;
    }

    @Override
    public RiskThreshold setThreshold(Long portfolioId, RiskThreshold threshold) {
        UserPortfolio portfolio = userPortfolioService.getPortfolioById(portfolioId);

        if (threshold.getMaxSingleStockPercentage() < 0 || threshold.getMaxSingleStockPercentage() > 100) {
            throw new IllegalArgumentException("Max single stock percentage must be between 0 and 100");
        }

        // Check if existing threshold
        RiskThreshold existing = riskThresholdRepository.findByPortfolioId(portfolioId).orElse(null);
        if (existing != null) {
            existing.setMaxSingleStockPercentage(threshold.getMaxSingleStockPercentage());
            existing.setMaxOverallVolatility(threshold.getMaxOverallVolatility());
            return riskThresholdRepository.save(existing);
        } else {
            threshold.setPortfolio(portfolio);
            return riskThresholdRepository.save(threshold);
        }
    }

    @Override
    public RiskThreshold getThresholdForPortfolio(Long portfolioId) {
        return riskThresholdRepository.findByPortfolioId(portfolioId)
                .orElse(null); // Or throw exception? Requirement says "Get thresholds configured for a
                               // portfolio". Returning null or optional is fine, but controller might want
                               // 404. Let's return null and handle in controller or throw.
        // Re-reading rules: "getThresholdForPortfolio(Long portfolioId)".
        // Usually better to return null if not configured, or throw.
    }
}
