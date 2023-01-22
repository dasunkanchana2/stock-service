package com.dasun.stockservice.stock;

import com.dasun.stockservice.exception.RecordNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStock() {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.lenient().when(stockRepository.save(stock)).thenReturn(stock);
        assertDoesNotThrow(() -> {
            stockService.addStock(stock);
        });
    }

    @Test
    void testGetStock() {
        int offset = 0;
        int limit = 1;
        when(stockRepository.findAll(PageRequest.of(offset, limit))).thenReturn((getStocks()));
        List<Stock> stockList = stockService.getAllStocks(offset, limit);
        Assertions.assertNotNull(stockList);
    }

    @Test
    void testRemoveStock() {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.lenient().when(stockService.addStock(stock)).thenReturn(stock);
        Mockito.lenient().when(stockRepository.findById(stock.getId()))
                .thenReturn(Optional.of(new Stock(1, "Apple USA", new BigDecimal(13.00), currentTime, 0)));
        assertDoesNotThrow(() -> {
            stockService.removeStock(stock.getId());
        });
    }

    @Test
    void testRemoveStockNotExisting() {
        assertThrows(RecordNotFoundException.class, () -> {
            stockService.removeStock(101);
        });
    }

    @Test
    void testViewStockNotExisting() {
        assertThrows(RecordNotFoundException.class, () -> {
            stockService.viewStock(101);
        });
    }

    @Test
    void testUpdateStockNotExisting() {
        assertThrows(RecordNotFoundException.class, () -> {
            stockService.updateStock(null, 101);
        });
    }

    @Test
    void testViewStock() {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.lenient().when(stockService.addStock(stock)).thenReturn(stock);
        Mockito.lenient().when(stockRepository.findById(stock.getId()))
                .thenReturn(Optional.of(new Stock(1, "Apple USA", new BigDecimal(13.00), currentTime, 0)));
        Stock stockView = stockService.viewStock(stock.getId());
        assertEquals(1, stockView.getId());
    }

    @Test
    void testUpdateStock() {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.lenient().when(stockService.addStock(stock)).thenReturn(stock);
        Mockito.lenient().when(stockRepository.findById(stock.getId()))
                .thenReturn(Optional.of(new Stock(1, "Apple USA", new BigDecimal(13.00), currentTime, 0)));
        assertDoesNotThrow(() -> {
            Stock updatedStock = stockService.updateStock(stock, 1);
        });
    }

    private Page<Stock> getStocks() {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        List<Stock> stocks = new ArrayList<>();
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        stocks.add(stock);
        return new PageImpl(stocks);
    }
}
