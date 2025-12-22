package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return service.createStock(stock);
    }

    @GetMapping
    public List<Stock> all() {
        return service.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stock get(@PathVariable Long id) {
        return service.getStockById(id);
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateStock(id);
    }
}
