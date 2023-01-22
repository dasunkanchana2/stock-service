package com.dasun.stockservice.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface for perform stock related persistence operations by inheriting
 * features from {JpaRepository} interface.
 */
@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
}
