package com.example.demo.controller;

import com.example.demo.entity.PortfolioHolding;
import com.example.demo.service.PortfolioHoldingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Holdings", description = "Portfolio Holdings management APIs")
public class PortfolioHoldingController {

    private final PortfolioHoldingService portfolioHoldingService;

    public PortfolioHoldingController(PortfolioHoldingService portfolioHoldingService) {
        this.portfolioHoldingService = portfolioHoldingService;
    }

    @PostMapping("/{portfolioId}/{stockId}")
    @Operation(summary = "Add a holding for a portfolio and stock")
    public ResponseEntity<PortfolioHolding> addHolding(@PathVariable Long portfolioId,
            @PathVariable Long stockId,
            @RequestBody PortfolioHolding holding) {
        return ResponseEntity.ok(portfolioHoldingService.addHolding(portfolioId, stockId, holding));
    }

    @GetMapping("/portfolio/{portfolioId}")
    @Operation(summary = "List holdings for a portfolio")
    public ResponseEntity<List<PortfolioHolding>> getHoldingsByPortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(portfolioHoldingService.getHoldingsByPortfolio(portfolioId));
    }
}
