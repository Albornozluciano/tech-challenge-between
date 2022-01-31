package com.between.techchallenge.service;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.repository.PricesRepository;
import com.between.techchallenge.util.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PricesServiceTest {

    @Autowired
    PricesService pricesService;

    @Mock
    PricesRepository pricesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void priceNotFound() throws CustomException {
        Date date = new Date();
        Mockito.when(pricesRepository.getPricesByBrandProductAndDate(any(), any(), any())).thenReturn(null);

        String brandId = "1234";
        String productId = "1234";

        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            pricesService.getPriceByBrandProductAndDate(brandId, productId, date);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.PRICE_NOT_FOUND.getType(), thrown.getApiError().getType());
        assertEquals("Price error not found with values BrandId: '" + brandId + "',  ProductId: '" + productId + "', ApplicationDate: '" + DateUtils.fromDateToString(date) + "'.", thrown.getApiError().getDetail());
    }

    @Test
    public void retrieveExistentPrice() throws CustomException {
        String brandId = "1";
        String productId = "35455";
        Date date = DateUtils.fromStringToDate("2020-06-14-15.00.00");

        BrandProductPriceDTO brandProductPriceDTO = pricesService.getPriceByBrandProductAndDate(brandId, productId, date);
        assertNotNull(brandProductPriceDTO);
    }

    @Test
    public void errorOnDBShouldThrowCustomException() {
        Mockito.when(pricesRepository.getPricesByBrandProductAndDate(any(), any(), any())).thenThrow(JpaSystemException.class);

        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            pricesService.getPriceByBrandProductAndDate("brandId", "productId", new Date());
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.BD_INTERNAL_SERVER_ERROR.getType(), thrown.getApiError().getType());
        assertEquals("Error getting price from database.", thrown.getApiError().getDetail());
    }
}