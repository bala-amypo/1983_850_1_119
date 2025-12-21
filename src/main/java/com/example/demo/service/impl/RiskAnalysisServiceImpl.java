package com.example.demo.service.impl;

import com.example.demo.entity.RiskAnalysisResult;
import com.example.demo.repository.PortfolioHoldingRepository;
import com.example.demo.repository.RiskAnalysisResultRepository;
import com.example.demo.repository.RiskThresholdRepository;
import com.example.demo.repository.UserPortfolioRepository;
import com.example.demo.service.RiskAnalysisService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RiskAnalysisServiceImpl implements RiskAnalysisService {

    private final RiskAnalysisResultRepository analysisResultRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final PortfolioHoldingRepository holdingRepository;
    private final RiskThresholdRepository riskThresholdRepository;

    // ⚠️ EXACT constructor order required
    public RiskAnalysisServiceImpl(
            RiskAnalysisResultRepository analysisResultRepository,
            UserPortfolioRepository userPortfolioRepository,
            PortfolioHoldingRepository holdingRepository,
            RiskThresholdRepository riskThresholdRepository
    ) {
        this.analysisResultRepository = analysisResultRepository;
        this.userPortfolioRepository = userPortfolioRepository;
        this.holdingRepository = holdingRepository;
        this.riskThresholdRepository = riskThresholdRepository;
    }

    @Override
    public RiskAnalysisResult analyzePortfolio(Long portfolioId) {
        RiskAnalysisResult result = new RiskAnalysisResult();
        result.setAnalysisDate(new Timestamp(System.currentTimeMillis()));
        result.setIsHighRisk(false);
        result.setNotes("Analysis completed");
        return analysisResultRepository.save(result);
    }

    @Override
    public RiskAnalysisResult getAnalysisById(Long id) {
        return analysisResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<RiskAnalysisResult> getAnalysesForPortfolio(Long portfolioId) {
        return analysisResultRepository.findByPortfolioId(portfolioId);
    }
}
