package com.example.demo.controller;

import com.example.demo.model.UserPortfolio;
import com.example.demo.service.UserPortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@Tag(name = "Portfolios", description = "User Portfolio management APIs")
public class UserPortfolioController {

    private final UserPortfolioService userPortfolioService;

    public UserPortfolioController(UserPortfolioService userPortfolioService) {
        this.userPortfolioService = userPortfolioService;
    }

    @PostMapping
    @Operation(summary = "Create a new portfolio")
    public ResponseEntity<UserPortfolio> createPortfolio(@RequestBody UserPortfolio portfolio) {
        return ResponseEntity.ok(userPortfolioService.createPortfolio(portfolio));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get portfolio details by ID")
    public ResponseEntity<UserPortfolio> getPortfolioById(@PathVariable Long id) {
        return ResponseEntity.ok(userPortfolioService.getPortfolioById(id));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "List portfolios for a specific user")
    public ResponseEntity<List<UserPortfolio>> getPortfoliosByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userPortfolioService.getPortfoliosByUser(userId));
    }
}
