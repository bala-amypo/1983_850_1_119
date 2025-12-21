package com.example.demo.controller;

import com.example.demo.entity.PortfolioHolding;
import com.example.demo.service.PortfolioHoldingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Portfolio Holding Controller")
public class PortfolioHoldingController {

    private final PortfolioHoldingService holdingService;

    public PortfolioHoldingController(PortfolioHoldingService holdingService) {
        this.holdingService = holdingService;
    }

    @PostMapping
    public PortfolioHolding createHolding(@RequestBody PortfolioHolding holding) {
        return holdingService.createHolding(holding);
    }

    @PutMapping("/{id}")
    public PortfolioHolding updateHolding(@PathVariable Long id,
                                          @RequestBody PortfolioHolding holding) {
        return holdingService.updateHolding(id, holding);
    }

    @GetMapping("/{id}")
    public PortfolioHolding getHolding(@PathVariable Long id) {
        return holdingService.getHoldingById(id);
    }

    @GetMapping("/portfolio/{portfolioId}")
    public List<PortfolioHolding> getHoldingsByPortfolio(@PathVariable Long portfolioId) {
        return holdingService.getHoldingsByPortfolio(portfolioId);
    }

    @DeleteMapping("/{id}")
    public void deleteHolding(@PathVariable Long id) {
        holdingService.deleteHolding(id);
    }
}
