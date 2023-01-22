package com.dasun.stockservice.stock;

import com.dasun.stockservice.constants.ActivityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This is an end point to consumed client's HTTP requests for stock creation. This will consume
 * any of http method with an any of content types and return JSON responses according to the client requests.
 */

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    /**
     * get all stocks
     *
     * @return List of Stock objects
     */
    @GetMapping("/stocks")
    public ResponseEntity<?> getAllStocks(@RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit) {
        long startTime = System.currentTimeMillis();
        List<Stock> stockList = stockService.getAllStocks(offset, limit);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info(ActivityType.GET_ALL_STOCKS + ": response={} | timeTaken={}", stockList, timeTaken);
        return new ResponseEntity<>(stockList, HttpStatus.OK);
    }


    /**
     * add stock
     *
     * @param stock : Stock object
     * @return : Saved Stock object
     */
    @PostMapping("/stocks")
    public ResponseEntity<?> addStock(@Valid @RequestBody Stock stock) {
        long startTime = System.currentTimeMillis();
        Stock dbStock = stockService.addStock(stock);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info(ActivityType.ADD_STOCK + ": response={} | timeTaken={}", dbStock, timeTaken);
        return new ResponseEntity<>(dbStock, HttpStatus.CREATED);
    }


    /**
     * view particular stock
     *
     * @param id : stock id
     * @return : Stock object
     */
    @GetMapping("/stocks/{id}")
    public ResponseEntity<?> viewStock(@PathVariable("id") int id) {
        long startTime = System.currentTimeMillis();
        Stock stock = stockService.viewStock(id);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info(ActivityType.VIEW_STOCK + ": response={} | timeTaken={}", stock, timeTaken);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    /**
     * update particular stock
     *
     * @param id    : Stock id
     * @param stock : Stock obj
     * @return : Upt Stock obj
     */
    @PatchMapping("/stocks/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") int id,
                                         @Valid @RequestBody Stock stock) {
        long startTime = System.currentTimeMillis();
        Stock updateStock = stockService.updateStock(stock, id);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info(ActivityType.UPDATE_STOCK + ": response={} | timeTaken={}", updateStock, timeTaken);
        return new ResponseEntity<>(updateStock, HttpStatus.OK);
    }

    /**
     * update particular stock
     *
     * @param id    : Stock id
     */
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<?> removeStock(@PathVariable("id") int id) {
        long startTime = System.currentTimeMillis();
        stockService.removeStock(id);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info(ActivityType.DELETE_STOCK + ": | timeTaken={}", timeTaken);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }
}
