package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.UserPortfolioRepository;
import com.example.demo.service.UserPortfolioService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPortfolioServiceImpl implements UserPortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;
    private final UserService userService;

    public UserPortfolioServiceImpl(UserPortfolioRepository userPortfolioRepository, UserService userService) {
        this.userPortfolioRepository = userPortfolioRepository;
        this.userService = userService;
    }

    @Override
    public UserPortfolio createPortfolio(UserPortfolio portfolio) {
        if (portfolio.getUser() == null || portfolio.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        User user = userService.findById(portfolio.getUser().getId());
        portfolio.setUser(user);
        if (portfolio.getCreatedAt() == null) {
            portfolio.setCreatedAt(LocalDateTime.now());
        }
        if (portfolio.getPortfolioName() == null || portfolio.getPortfolioName().isBlank()) {
            throw new IllegalArgumentException("Portfolio name cannot be empty");
        }
        return userPortfolioRepository.save(portfolio);
    }

    @Override
    public UserPortfolio getPortfolioById(Long id) {
        return userPortfolioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + id));
    }

    @Override
    public List<UserPortfolio> getPortfoliosByUser(Long userId) {
        return userPortfolioRepository.findByUserId(userId);
    }
}
