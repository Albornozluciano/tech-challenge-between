package com.between.techchallenge.service;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.CustomException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface PricesService {
    BrandProductPriceDTO getPriceByBrandProductAndDate(String brandId, String productId, Date applicationDate) throws CustomException;
}
