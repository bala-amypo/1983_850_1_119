package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // EXACT NAME REQUIRED BY TEST
    Optional<Stock> findByTicker(String ticker);
}
