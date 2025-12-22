package com.example.demo.service;

import com.example.demo.entity.PortfolioHolding;

import java.util.List;

public interface PortfolioHoldingService {

    PortfolioHolding createHolding(PortfolioHolding holding);

    PortfolioHolding updateHolding(Long id, PortfolioHolding holding);

    PortfolioHolding getHoldingById(Long id);

    List<PortfolioHolding> getHoldingsByPortfolio(Long portfolioId);

    void deleteHolding(Long id);
}
