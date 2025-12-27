package com.example.demo.controller;

import com.example.demo.service.RiskThresholdService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskThresholdController {
    private final RiskThresholdService service;

    public RiskThresholdController(RiskThresholdService service) {
        this.service = service;
    }
}