package com.example.stock_exchange.service;

import com.example.stock_exchange.errors.NotFoundException;
import com.example.stock_exchange.model.Stock;
import com.example.stock_exchange.repository.StockRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StockServiceTest {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void createStock() {
        Stock newStock = new Stock();
        newStock.setName("Some new Inc.");
        newStock.setCurrentPrice(new BigDecimal("140.00"));

        stockService.createStock(newStock);

        Stock stock = stockRepository.findByNameIgnoreCase(newStock.getName()).orElseThrow();
        assertEquals(newStock, stock);
    }

    @Test
    void deleteStock() {
        String toBeDeleted = "Amazon.com Inc.";

        stockService.deleteStock(toBeDeleted);

        assertFalse(stockRepository.findByNameIgnoreCase(toBeDeleted).isPresent());
    }

    @Test
    void updateStockPrice() {
        Stock newPrice = new Stock();
        newPrice.setName("Amazon.com Inc.");
        newPrice.setCurrentPrice(BigDecimal.valueOf(140.00));

        Stock updatedStock = stockService.updateStockPrice(newPrice);

        assertEquals(BigDecimal.valueOf(140.00), updatedStock.getCurrentPrice());
    }
}