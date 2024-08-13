package com.example.stock_exchange.service;

import com.example.stock_exchange.errors.NotFoundException;
import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.model.StockExchange;
import com.example.stock_exchange.repository.StockExchangeRepository;
import com.example.stock_exchange.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    public void deleteStock(String stockName) {
        Stock toBeDeleted = stockRepository.findByNameIgnoreCase(stockName).orElseThrow(() -> new NotFoundException("Stock not found"));

        for (StockExchange se : toBeDeleted.getStockExchanges()) {
            se.getStocks().remove(toBeDeleted);
        }

        stockRepository.save(toBeDeleted);
        stockRepository.delete(toBeDeleted);
    }

    @Transactional
    public synchronized Stock updateStockPrice(Stock update) {
        Stock stock = stockRepository.findByNameIgnoreCase(update.getName()).orElseThrow(() -> new NotFoundException("Stock not found"));
        stock.setCurrentPrice(update.getCurrentPrice());
        stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        return stockRepository.save(stock);
    }
}
