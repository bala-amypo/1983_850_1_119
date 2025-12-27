// package com.example.demo.controller;

// import com.example.demo.model.RiskAnalysisResult;
// import com.example.demo.service.RiskAnalysisService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/risk-analysis")
// @Tag(name = "Risk Analysis", description = "Risk Analysis APIs")
// public class RiskAnalysisController {

//     private final RiskAnalysisService riskAnalysisService;

//     public RiskAnalysisController(RiskAnalysisService riskAnalysisService) {
//         this.riskAnalysisService = riskAnalysisService;
//     }

//     @PostMapping("/{portfolioId}")
//     @Operation(summary = "Run risk analysis for a portfolio")
//     public ResponseEntity<RiskAnalysisResult> analyzePortfolio(@PathVariable Long portfolioId) {
//         return ResponseEntity.ok(riskAnalysisService.analyzePortfolio(portfolioId));
//     }

//     @GetMapping("/{portfolioId}")
//     @Operation(summary = "Get all analysis results for a portfolio")
//     public ResponseEntity<List<RiskAnalysisResult>> getAnalysesForPortfolio(@PathVariable Long portfolioId) {
//         return ResponseEntity.ok(riskAnalysisService.getAnalysesForPortfolio(portfolioId));
//     }
// }

package com.example.demo.controller;

import com.example.demo.model.RiskAnalysisResult;
import com.example.demo.service.RiskAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-analysis")
@Tag(name = "Risk Analysis", description = "Risk Analysis APIs")
public class RiskAnalysisController {

    private final RiskAnalysisService riskAnalysisService;

    public RiskAnalysisController(RiskAnalysisService riskAnalysisService) {
        this.riskAnalysisService = riskAnalysisService;
    }

    @PostMapping("/{portfolioId}")
    @Operation(summary = "Run risk analysis for a portfolio")
    public ResponseEntity<RiskAnalysisResult> analyzePortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(riskAnalysisService.analyzePortfolio(portfolioId));
    }

    @GetMapping("/{portfolioId}")
    @Operation(summary = "Get all analysis results for a portfolio")
    public ResponseEntity<List<RiskAnalysisResult>> getAnalysesForPortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(riskAnalysisService.getAnalysesForPortfolio(portfolioId));
    }
}
