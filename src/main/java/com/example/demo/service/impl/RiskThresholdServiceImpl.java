package com.example.demo.service.impl;

import com.example.demo.entity.RiskThreshold;
import com.example.demo.repository.RiskThresholdRepository;
import com.example.demo.service.RiskThresholdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskThresholdServiceImpl implements RiskThresholdService {

    private final RiskThresholdRepository riskThresholdRepository;

    // ⚠️ EXACT constructor order required
    public RiskThresholdServiceImpl(RiskThresholdRepository riskThresholdRepository) {
        this.riskThresholdRepository = riskThresholdRepository;
    }

    @Override
    public RiskThreshold createThreshold(RiskThreshold threshold) {
        return riskThresholdRepository.save(threshold);
    }

    @Override
    public RiskThreshold updateThreshold(Long id, RiskThreshold threshold) {
        getThresholdById(id);
        threshold.setId(id);
        return riskThresholdRepository.save(threshold);
    }

    @Override
    public RiskThreshold getActiveThreshold() {
        return riskThresholdRepository.findByActiveTrue()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public RiskThreshold getThresholdById(Long id) {
        return riskThresholdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<RiskThreshold> getAllThresholds() {
        return riskThresholdRepository.findAll();
    }
}
