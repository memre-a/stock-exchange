package com.example.stock_exchange.model;

import com.example.stock_exchange.model.validation.StockExValidation;
import com.example.stock_exchange.model.validation.StockValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {StockValidation.class, StockExValidation.class}, message = "Stock name cannot be empty")
    private String name;

    private String description;

    @NotNull(groups = StockValidation.class, message = "Current price is a mandatory value")
    @DecimalMin(groups = StockValidation.class, value = "0.0", inclusive = false, message = "Current price must be greater than zero")
    private BigDecimal currentPrice;

    @UpdateTimestamp
    private Timestamp lastUpdate;

    @JsonIgnore
    @ManyToMany(mappedBy = "stocks")
    private Set<StockExchange> stockExchanges;

    @JsonIgnore
    @Version
    private int version;

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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<StockExchange> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(Set<StockExchange> stockExchanges) {
        this.stockExchanges = stockExchanges;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
