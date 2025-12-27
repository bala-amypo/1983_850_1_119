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

    public PortfolioHoldingServiceImpl(
            PortfolioHoldingRepository portfolioHoldingRepository,
            UserPortfolioService userPortfolioService,
            StockService stockService) {
        this.portfolioHoldingRepository = portfolioHoldingRepository;
        this.userPortfolioService = userPortfolioService;
        this.stockService = stockService;
    }

    @Override
    public PortfolioHolding addHolding(Long portfolioId, Long stockId, PortfolioHolding holding) {

        UserPortfolio portfolio = userPortfolioService.getPortfolioById(portfolioId);

        // IMPORTANT: service returns Stock (not ResponseEntity)
        Stock stock = stockService.getStockById(stockId);

        if (holding.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (holding.getMarketValue() == null ||
                holding.getMarketValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Market value must be non-negative");
        }

        holding.setPortfolio(portfolio);
        holding.setStock(stock);

        return portfolioHoldingRepository.save(holding);
    }

    @Override
    public List<PortfolioHolding> getHoldingsByPortfolio(Long portfolioId) {
        return portfolioHoldingRepository.findByPortfolioId(portfolioId);
    }
}
