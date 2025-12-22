package com.example.demo.service;

import com.example.demo.model.Stock;
import java.util.List;

public interface StockService {
    Stock saveStock(Stock stock);
    List<Stock> getAllStocks();
    Stock getStockById(Long id);
}