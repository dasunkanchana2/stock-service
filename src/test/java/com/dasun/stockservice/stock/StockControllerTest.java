package com.dasun.stockservice.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetStock() throws Exception {
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
        Mockito.when(stockService.getAllStocks(0, 1)).thenReturn(stocks);

        final MockHttpServletResponse response = mockMvc.perform(get("/stocks")
                .param("offset", "0")
                .param("limit", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testAddStock() throws Exception {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.when(stockService.addStock(ArgumentMatchers.any())).thenReturn(stock);
        String json = mapper.writeValueAsString(stock);
        final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/stocks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void testRemoveStock() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(delete("/stocks/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Successfully Deleted");
        verify(stockService).removeStock(0);
    }

    @Test
    void testViewStock() throws Exception {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.when(stockService.viewStock(1)).thenReturn(stock);
        final MockHttpServletResponse response = mockMvc.perform(get("/stocks/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateStock() throws Exception {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Apple USA");
        stock.setCurrentPrice(new BigDecimal(13.00));
        stock.setIsDeleted(0);
        stock.setLastUpdate(currentTime);
        Mockito.when(stockService.updateStock(any(Stock.class), eq(0))).thenReturn(stock);
        String json = mapper.writeValueAsString(stock);
        final MockHttpServletResponse response = mockMvc.perform(patch("/stocks/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
