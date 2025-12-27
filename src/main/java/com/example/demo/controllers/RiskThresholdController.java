package com.example.demo.controller;

import com.example.demo.model.RiskThreshold;
import com.example.demo.service.RiskThresholdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-thresholds")
@Tag(name = "Risk Thresholds", description = "Risk Threshold management APIs")
public class RiskThresholdController {

    private final RiskThresholdService riskThresholdService;

    public RiskThresholdController(RiskThresholdService riskThresholdService) {
        this.riskThresholdService = riskThresholdService;
    }

    @PostMapping("/{portfolioId}")
    @Operation(summary = "Create or update thresholds for a portfolio")
    public ResponseEntity<RiskThreshold> setThreshold(@PathVariable Long portfolioId,
            @RequestBody RiskThreshold threshold) {
        return ResponseEntity.ok(riskThresholdService.setThreshold(portfolioId, threshold));
    }

    @GetMapping("/{portfolioId}")
    @Operation(summary = "Get thresholds configured for a portfolio")
    public ResponseEntity<RiskThreshold> getThreshold(@PathVariable Long portfolioId) {
        RiskThreshold threshold = riskThresholdService.getThresholdForPortfolio(portfolioId);
        // If null, could return 404 or just return null body. Returning null body with
        // 200 OK or 204 No Content is often acceptable if "not found" isn't an error.
        // But since ResourceNotFoundException is standard, maybe check it.
        // For now returning the result even if null.
        return ResponseEntity.ok(threshold);
    }
}
