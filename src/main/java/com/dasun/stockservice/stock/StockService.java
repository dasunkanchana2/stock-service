package com.dasun.stockservice.stock;

import java.util.List;

/**
 * This is a service interface for perform stock related business operations
 */
public interface StockService {

    List<Stock> getAllStocks(int offset, int limit);

    Stock addStock(Stock stock);

    Stock viewStock(int id);

    Stock updateStock(Stock stock, int id);

    void removeStock(int id);

}
