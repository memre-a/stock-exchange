package com.example.stock_exchange.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "StockExchange")
public class StockExchange {
    public static final int LIVE_IN_MARKET_THRESHOLD = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Version
    private int version;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stock_exchange_stocks",
            joinColumns = @JoinColumn(name = "stock_exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private Set<Stock> stocks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public boolean isLiveInMarket() {
        return stocks.size() >= LIVE_IN_MARKET_THRESHOLD;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
