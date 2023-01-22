package com.dasun.stockservice.stock;

import com.dasun.stockservice.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is an implementation of {StockService} to handle all
 * business operations related to stock
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    /**
     * get all stocks
     *
     * @param offset : offset
     * @param limit  : limit
     * @return : list<Stock>
     */
    @Override
    public List<Stock> getAllStocks(int offset, int limit) {
        return stockRepository.findAll(PageRequest.of(offset, limit)).stream()
                .filter(stock -> stock.getIsDeleted() == 0)
                .collect(Collectors.toList());
    }

    /**
     * add stock
     *
     * @param stock : Stock object
     * @return : Created Stock object
     */
    @Override
    public Stock addStock(Stock stock) {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());

        Stock stockObj = new Stock();
        stockObj.setName(stock.getName());
        stockObj.setCurrentPrice(stock.getCurrentPrice());
        stockObj.setIsDeleted(0);
        stockObj.setLastUpdate(currentTime);
        Stock stockCreated = stockRepository.save(stockObj);
        logger.info("Created stock");
        return stockRepository.save(stockCreated);
    }

    /**
     * view particular stock
     *
     * @param id : stock id
     * @return : Stock object
     */
    @Override
    public Stock viewStock(int id) {
        Optional<Stock> optStock = stockRepository.findById(id);
        if (optStock.isEmpty()) {
            logger.info("Stock Not Found for id :{}", id);
            throw new RecordNotFoundException("Stock Not Found");
        } else if (optStock.isPresent()) {
            if (optStock.get().getIsDeleted() == 1) {
                logger.info("Stock Not Found for id :{}", id);
                throw new RecordNotFoundException("Stock Not Found");
            }
        }
        return optStock.get();
    }

    /**
     * Update stock
     *
     * @param stock : Stock object
     * @param id    : stock id
     * @return : Updated Stock
     */
    @Override
    public Stock updateStock(Stock stock, int id) {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Optional<Stock> optStock = stockRepository.findById(id);
        if (optStock.isEmpty()) {
            logger.info("Stock Not Found for id :{}", id);
            throw new RecordNotFoundException("Stock Not Found");
        }
        optStock.get().setName(stock.getName());
        optStock.get().setCurrentPrice(stock.getCurrentPrice());
        optStock.get().setLastUpdate(currentTime);
        Stock updtStock = stockRepository.save(optStock.get());
        logger.info("Updated stock");
        return updtStock;
    }

    /**
     * remove stock
     *
     * @param id : stock id
     */
    @Override
    public void removeStock(int id) {
        Optional<Stock> optStock = stockRepository.findById(id);
        if (optStock.isEmpty()) {
            logger.info("Stock Not Found for id :{}", id);
            throw new RecordNotFoundException("Stock Not Found");
        }
        optStock.get().setIsDeleted(1);
        Stock updtStock = stockRepository.save(optStock.get());
        logger.info("Deleted stock");
    }
}
