package com.example.demo.controller;

import com.example.demo.model.RiskThreshold;
import com.example.demo.service.RiskThresholdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk-thresholds")
public class RiskThresholdController {

    private final RiskThresholdService service;

    public RiskThresholdController(RiskThresholdService service) {
        this.service = service;
    }

    @PostMapping("/{portfolioId}")
    public RiskThreshold set(@PathVariable Long portfolioId,
                             @RequestBody RiskThreshold threshold) {
        return service.setThreshold(portfolioId, threshold);
    }

    @GetMapping("/{portfolioId}")
    public RiskThreshold get(@PathVariable Long portfolioId) {
        return service.getThreshold(portfolioId);
    }
}
