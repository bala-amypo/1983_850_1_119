package com.example.demo.controller;

import com.example.demo.model.UserPortfolio;
import com.example.demo.service.UserPortfolioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class UserPortfolioController {

    private final UserPortfolioService service;

    public UserPortfolioController(UserPortfolioService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public UserPortfolio create(@PathVariable Long userId,
                                @RequestBody UserPortfolio portfolio) {
        return service.createPortfolio(userId, portfolio);
    }

    @GetMapping("/{id}")
    public UserPortfolio get(@PathVariable Long id) {
        return service.getPortfolioById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserPortfolio> getByUser(@PathVariable Long userId) {
        return service.getPortfoliosByUser(userId);
    }
}
