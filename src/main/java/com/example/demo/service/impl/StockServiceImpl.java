package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(Stock stock) {
        if (stockRepository.findByTicker(stock.getTicker()).isPresent()) {
            throw new IllegalArgumentException(
                    "Stock with ticker " + stock.getTicker() + " already exists"
            );
        }
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock existing = getStockEntityById(id);
        existing.setTicker(stock.getTicker());
        existing.setCompanyName(stock.getCompanyName());
        existing.setSector(stock.getSector());
        existing.setIsActive(stock.getIsActive());
        return stockRepository.save(existing);
    }

    @Override
    public ResponseEntity<Stock> getStockById(Long id) {
        return stockRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock not found with id: " + id)
                );
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void deactivateStock(Long id) {
        Stock stock = getStockEntityById(id);
        stock.setIsActive(false);
        stockRepository.save(stock);
    }

    /**
     * Internal helper method (keeps service logic clean)
     */
    private Stock getStockEntityById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock not found with id: " + id)
                );
    }
}
