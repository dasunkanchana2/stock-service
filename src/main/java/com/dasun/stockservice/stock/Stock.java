package com.dasun.stockservice.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * This is a model class to hold stock data
 * from stock table in the stock db
 */

@Entity
@Table(name = "stock")
public class Stock {

    public Stock() {
        //no-args constructor
    }

    public Stock(int id, @NotNull String name, @NotNull BigDecimal currentPrice, Timestamp lastUpdate, @NotNull int isDeleted) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
        this.isDeleted = isDeleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private int id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal currentPrice;

    @JsonIgnore
    private Timestamp lastUpdate;

    @JsonIgnore
    @NotNull
    private int isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
