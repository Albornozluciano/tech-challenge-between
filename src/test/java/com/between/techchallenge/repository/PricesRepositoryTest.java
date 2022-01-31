package com.between.techchallenge.repository;

import com.between.techchallenge.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PricesRepositoryTest {
    @Autowired
    PricesRepository pricesRepository;

    @Test
    public void retrievePriceSuccessfully() {
        Price price = pricesRepository.getPricesByBrandProductAndDate("1", "35455", "2020-06-14-00.00.00");
        assertNotNull(price);
    }

    @Test
    public void priceNotFound() {
        Price price = pricesRepository.getPricesByBrandProductAndDate("1000", "10000", "1000");
        assertNull(price);
    }
}