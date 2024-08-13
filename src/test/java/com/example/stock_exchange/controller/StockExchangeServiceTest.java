package com.example.stock_exchange.controller;

import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.model.StockExchange;
import com.example.stock_exchange.repository.StockExchangeRepository;
import com.example.stock_exchange.repository.StockRepository;
import com.example.stock_exchange.service.StockExchangeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StockExchangeServiceTest {
    private static final String NYSE = "NYSE";
    private static final String NASDAQ = "NASDAQ";

    @Autowired
    private StockExchangeService stockExchangeService;

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getStockExchange() {
        StockExchange exchange = stockExchangeService.getStockExchangeByName(NASDAQ);

        assertEquals(4, exchange.getStocks().size());
    }

    @Test
    void addStockToExchange() {
        Stock newStock = new Stock();
        newStock.setName("Some new Inc.");
        newStock.setCurrentPrice(new BigDecimal("140.00"));
        newStock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        stockRepository.save(newStock);

        StockExchange updatedExchange = stockExchangeService.addStockToExchange(NYSE, newStock);

        assertEquals(6, updatedExchange.getStocks().size());
    }

    @Test
    void removeStockFromExchange() {
        Stock toBeDeleted = new Stock();
        toBeDeleted.setName("Amazon.com Inc.");

        StockExchange updatedExchange = stockExchangeService.removeStockFromExchange(NASDAQ, toBeDeleted);

        assertEquals(3, updatedExchange.getStocks().size());
    }
}