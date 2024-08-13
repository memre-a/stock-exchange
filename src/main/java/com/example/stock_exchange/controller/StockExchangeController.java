package com.example.stock_exchange.controller;

import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.model.StockExchange;
import com.example.stock_exchange.model.validation.StockExValidation;
import com.example.stock_exchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {
    @Autowired
    private StockExchangeService stockExchangeService;

    @GetMapping("/{name}")
    public ResponseEntity<StockExchange> getStockExchange(@PathVariable String name) {
        return ResponseEntity.ok(stockExchangeService.getStockExchangeByName(name));
    }

    @PostMapping("/{name}")
    public ResponseEntity<StockExchange> addStockToExchange(@PathVariable String name, @Validated(StockExValidation.class) @RequestBody Stock stock) {
        return ResponseEntity.ok(stockExchangeService.addStockToExchange(name, stock));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<StockExchange> removeStockFromExchange(@PathVariable String name,@Validated(StockExValidation.class) @RequestBody Stock stock) {
        return ResponseEntity.ok(stockExchangeService.removeStockFromExchange(name, stock));
    }
}
