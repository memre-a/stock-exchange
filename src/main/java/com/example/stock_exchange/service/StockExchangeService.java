package com.example.stock_exchange.service;

import com.example.stock_exchange.errors.NotFoundException;
import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.model.StockExchange;
import com.example.stock_exchange.repository.StockExchangeRepository;
import com.example.stock_exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeService {
    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    public StockExchange getStockExchangeByName(String name) {
        return stockExchangeRepository.findByNameIgnoreCase(name).orElseThrow(() -> new NotFoundException("Stock Exchange not found"));
    }

    public StockExchange addStockToExchange(String exchangeName, Stock addStock) {
        StockExchange exchange = getStockExchangeByName(exchangeName);
        Stock stock = stockRepository.findByNameIgnoreCase(addStock.getName()).orElseThrow(() -> new NotFoundException("Stock Exchange not found"));

        exchange.getStocks().add(stock);

        return stockExchangeRepository.save(exchange);
    }

    public StockExchange removeStockFromExchange(String exchangeName, Stock stock) {
        StockExchange exchange = getStockExchangeByName(exchangeName);
        exchange.getStocks().removeIf(s -> s.getName().equals(stock.getName()));

        return stockExchangeRepository.save(exchange);
    }
}
