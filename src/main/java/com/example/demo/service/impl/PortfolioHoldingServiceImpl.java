package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.PortfolioHoldingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioHoldingServiceImpl implements PortfolioHoldingService {

    private final PortfolioHoldingRepository repo;
    private final UserPortfolioRepository portfolioRepo;
    private final StockRepository stockRepo;

    public PortfolioHoldingServiceImpl(PortfolioHoldingRepository repo,
                                       UserPortfolioRepository portfolioRepo,
                                       StockRepository stockRepo) {
        this.repo = repo;
        this.portfolioRepo = portfolioRepo;
        this.stockRepo = stockRepo;
    }

    public PortfolioHolding addHolding(Long portfolioId, Long stockId, PortfolioHolding holding) {
        if (holding.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        UserPortfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        Stock stock = stockRepo.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));

        holding.setPortfolio(portfolio);
        holding.setStock(stock);
        return repo.save(holding);
    }

    public List<PortfolioHolding> getHoldingsByPortfolio(Long portfolioId) {
        return repo.findByPortfolioId(portfolioId);
    }
}
