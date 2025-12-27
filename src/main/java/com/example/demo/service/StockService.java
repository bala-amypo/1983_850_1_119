package com.example.demo.service;

import com.example.demo.model.Stock;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StockService {

    Stock createStock(Stock stock);

    Stock updateStock(Long id, Stock stock);

    ResponseEntity<Stock> getStockById(Long id);

    List<Stock> getAllStocks();

    void deactivateStock(Long id);
}
