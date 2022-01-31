package com.between.techchallenge.controller;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.service.PricesService;
import com.between.techchallenge.util.DateUtils;
import com.between.techchallenge.validator.RequestFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PricesController {
    @Autowired
    PricesService pricesService;

    @GetMapping("/v1/prices")
    public BrandProductPriceDTO getPriceByProductAndBrand(
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String applicationDate
    ) throws CustomException {
        RequestFieldsValidator.validatePathParams(brandId, productId, applicationDate);
        Date parsedApplicationDate = DateUtils.fromStringToDate(applicationDate);
        return pricesService.getPriceByBrandProductAndDate(brandId, productId, parsedApplicationDate);
    }
}