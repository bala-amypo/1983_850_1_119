package com.example.demo.controller;

import com.example.demo.entity.UserPortfolio;
import com.example.demo.service.UserPortfolioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@Tag(name = "User Portfolio Controller")
public class UserPortfolioController {

    private final UserPortfolioService userPortfolioService;

    public UserPortfolioController(UserPortfolioService userPortfolioService) {
        this.userPortfolioService = userPortfolioService;
    }

    @PostMapping
    public UserPortfolio createPortfolio(@RequestBody UserPortfolio portfolio) {
        return userPortfolioService.createPortfolio(portfolio);
    }

    @PutMapping("/{id}")
    public UserPortfolio updatePortfolio(@PathVariable Long id,
                                         @RequestBody UserPortfolio portfolio) {
        return userPortfolioService.updatePortfolio(id, portfolio);
    }

    @GetMapping("/{id}")
    public UserPortfolio getPortfolio(@PathVariable Long id) {
        return userPortfolioService.getPortfolioById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserPortfolio> getPortfoliosByUser(@PathVariable Long userId) {
        return userPortfolioService.getPortfoliosByUser(userId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivatePortfolio(@PathVariable Long id) {
        userPortfolioService.deactivatePortfolio(id);
    }
}
