package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.UserPortfolioRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserPortfolioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPortfolioServiceImpl implements UserPortfolioService {

    private final UserPortfolioRepository repo;
    private final UserRepository userRepo;

    public UserPortfolioServiceImpl(UserPortfolioRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public UserPortfolio createPortfolio(Long userId, UserPortfolio portfolio) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        portfolio.setUser(user);
        portfolio.setCreatedAt(LocalDateTime.now());
        return repo.save(portfolio);
    }

    public UserPortfolio getPortfolioById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));
    }

    public List<UserPortfolio> getPortfoliosByUser(Long userId) {
        return repo.findByUserId(userId);
    }
}
