package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RiskThreshold;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.RiskThresholdRepository;
import com.example.demo.repository.UserPortfolioRepository;
import com.example.demo.service.RiskThresholdService;
import org.springframework.stereotype.Service;

@Service
public class RiskThresholdServiceImpl implements RiskThresholdService {

    private final RiskThresholdRepository repo;
    private final UserPortfolioRepository portfolioRepo;

    public RiskThresholdServiceImpl(RiskThresholdRepository repo,
                                    UserPortfolioRepository portfolioRepo) {
        this.repo = repo;
        this.portfolioRepo = portfolioRepo;
    }

    public RiskThreshold setThreshold(Long portfolioId, RiskThreshold threshold) {
        if (threshold.getMaxSingleStockPercentage() < 0 ||
            threshold.getMaxSingleStockPercentage() > 100) {
            throw new IllegalArgumentException("Invalid percentage");
        }

        UserPortfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        threshold.setPortfolio(portfolio);
        return repo.save(threshold);
    }

    public RiskThreshold getThreshold(Long portfolioId) {
        return repo.findByPortfolioId(portfolioId);
    }
}
