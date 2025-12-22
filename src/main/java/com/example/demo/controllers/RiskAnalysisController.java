package com.example.demo.controller;

import com.example.demo.entity.RiskAnalysisResult;
import com.example.demo.service.RiskAnalysisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-analysis")
@Tag(name = "Risk Analysis Controller")
public class RiskAnalysisController {

    private final RiskAnalysisService riskAnalysisService;

    public RiskAnalysisController(RiskAnalysisService riskAnalysisService) {
        this.riskAnalysisService = riskAnalysisService;
    }

    @PostMapping("/analyze/{portfolioId}")
    public RiskAnalysisResult analyze(@PathVariable Long portfolioId) {
        return riskAnalysisService.analyzePortfolio(portfolioId);
    }

    @GetMapping("/{id}")
    public RiskAnalysisResult getAnalysis(@PathVariable Long id) {
        return riskAnalysisService.getAnalysisById(id);
    }

    @GetMapping("/portfolio/{portfolioId}")
    public List<RiskAnalysisResult> getAnalysesForPortfolio(@PathVariable Long portfolioId) {
        return riskAnalysisService.getAnalysesForPortfolio(portfolioId);
    }
}
