package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repo;

    public StockServiceImpl(StockRepository repo) {
        this.repo = repo;
    }

    public Stock createStock(Stock stock) {
        if (repo.findByTicker(stock.getTicker()).isPresent()) {
            throw new IllegalArgumentException("Ticker already exists");
        }
        stock.setIsActive(true);
        return repo.save(stock);
    }

    public Stock updateStock(Long id, Stock stock) {
        Stock s = getStockById(id);
        s.setCompanyName(stock.getCompanyName());
        s.setSector(stock.getSector());
        return repo.save(s);
    }

    public Stock getStockById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
    }

    public List<Stock> getAllStocks() {
        return repo.findAll();
    }

    public void deactivateStock(Long id) {
        Stock s = getStockById(id);
        s.setIsActive(false);
        repo.save(s);
    }
}
