package com.example.demo.controller;

import com.example.demo.model.PortfolioHolding;
import com.example.demo.service.PortfolioHoldingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
public class PortfolioHoldingController {

    private final PortfolioHoldingService service;

    public PortfolioHoldingController(PortfolioHoldingService service) {
        this.service = service;
    }

    @PostMapping("/{portfolioId}/{stockId}")
    public PortfolioHolding add(@PathVariable Long portfolioId,
                                @PathVariable Long stockId,
                                @RequestBody PortfolioHolding holding) {
        return service.addHolding(portfolioId, stockId, holding);
    }

    @GetMapping("/portfolio/{portfolioId}")
    public List<PortfolioHolding> list(@PathVariable Long portfolioId) {
        return service.getHoldingsByPortfolio(portfolioId);
    }
}
