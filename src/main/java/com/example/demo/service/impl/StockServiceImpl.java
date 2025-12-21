package com.example.demo.service.impl;

import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    // ⚠️ EXACT constructor order required
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(Stock stock) {
        stockRepository.findByTicker(stock.getTicker())
                .ifPresent(s -> {
                    throw new RuntimeException("Duplicate ticker");
                });
        stock.setActive(true);
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        getStockById(id);
        stock.setId(id);
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void deactivateStock(Long id) {
        Stock stock = getStockById(id);
        stock.setActive(false);
        stockRepository.save(stock);
    }
}
