package com.example.demo.service.impl;

import com.example.demo.model.PortfolioHolding;
import com.example.demo.model.Stock;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.PortfolioHoldingRepository;
import com.example.demo.service.PortfolioHoldingService;
import com.example.demo.service.StockService;
import com.example.demo.service.UserPortfolioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PortfolioHoldingServiceImpl implements PortfolioHoldingService {

    private final PortfolioHoldingRepository portfolioHoldingRepository;
    private final UserPortfolioService userPortfolioService;
    private final StockService stockService;

    public PortfolioHoldingServiceImpl(PortfolioHoldingRepository portfolioHoldingRepository,
            UserPortfolioService userPortfolioService,
            StockService stockService) {
        this.portfolioHoldingRepository = portfolioHoldingRepository;
        this.userPortfolioService = userPortfolioService;
        this.stockService = stockService;
    }

    @Override
    public PortfolioHolding addHolding(Long portfolioId, Long stockId, PortfolioHolding holding) {
        UserPortfolio portfolio = userPortfolioService.getPortfolioById(portfolioId);
        Stock stock = stockService.getStockById(stockId);

        if (holding.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (holding.getMarketValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Market value must be non-negative");
        }

        holding.setPortfolio(portfolio);
        holding.setStock(stock);
        return portfolioHoldingRepository.save(holding);
    }

    @Override
    public List<PortfolioHolding> getHoldingsByPortfolio(Long portfolioId) {
        // Validation of portfolio existence is implicit if we rely on finding it,
        // but here we just query. To follow strict rules, maybe we should check if
        // portfolio exists first.
        return portfolioHoldingRepository.findByPortfolioId(portfolioId);
    }
}
