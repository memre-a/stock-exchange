package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByNameIgnoreCase(String name);
}
