package com.example.stock_exchange.controller;

import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.model.validation.StockValidation;
import com.example.stock_exchange.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@Validated(StockValidation.class) @RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.createStock(stock));
    }

    @PutMapping
    public ResponseEntity<Stock> updateStockPrice(@Validated(StockValidation.class) @RequestBody Stock update) {
        return ResponseEntity.ok(stockService.updateStockPrice(update));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStock(@RequestParam String stockName) {
        stockService.deleteStock(stockName);
        return ResponseEntity.ok().build();
    }
}
