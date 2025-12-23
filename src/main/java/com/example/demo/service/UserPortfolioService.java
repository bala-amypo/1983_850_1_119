package com.example.demo.service;

import com.example.demo.entity.UserPortfolio;
import java.util.List;

public interface UserPortfolioService {
    UserPortfolio createPortfolio(UserPortfolio portfolio);

    UserPortfolio getPortfolioById(Long id);

    List<UserPortfolio> getPortfoliosByUser(Long userId);
}
