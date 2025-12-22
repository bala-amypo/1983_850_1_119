package com.example.demo.controller;

import com.example.demo.entity.RiskThreshold;
import com.example.demo.service.RiskThresholdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-thresholds")
@Tag(name = "Risk Threshold Controller")
public class RiskThresholdController {

    private final RiskThresholdService riskThresholdService;

    public RiskThresholdController(RiskThresholdService riskThresholdService) {
        this.riskThresholdService = riskThresholdService;
    }

    @PostMapping
    public RiskThreshold createThreshold(@RequestBody RiskThreshold threshold) {
        return riskThresholdService.createThreshold(threshold);
    }

    @PutMapping("/{id}")
    public RiskThreshold updateThreshold(@PathVariable Long id,
                                         @RequestBody RiskThreshold threshold) {
        return riskThresholdService.updateThreshold(id, threshold);
    }

    @GetMapping("/active")
    public RiskThreshold getActiveThreshold() {
        return riskThresholdService.getActiveThreshold();
    }

    @GetMapping("/{id}")
    public RiskThreshold getThreshold(@PathVariable Long id) {
        return riskThresholdService.getThresholdById(id);
    }

    @GetMapping
    public List<RiskThreshold> getAllThresholds() {
        return riskThresholdService.getAllThresholds();
    }
}
