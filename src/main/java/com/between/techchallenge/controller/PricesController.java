package com.between.techchallenge.controller;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.service.PricesService;
import com.between.techchallenge.util.DateUtils;
import com.between.techchallenge.validator.RequestFieldsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PricesController {
    Logger logger = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    PricesService pricesService;

    @GetMapping("/v1/prices")
    public BrandProductPriceDTO getPriceByProductAndBrand(
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String applicationDate
    ) throws CustomException {
        logger.debug("Validating path params: " + "ProductId = " + productId + ", BrandId = " + brandId + ", ApplicationDate = " + applicationDate);
        RequestFieldsValidator.validatePathParams(brandId, productId, applicationDate);

        logger.debug("Parsing ApplicationDate");
        Date parsedApplicationDate = DateUtils.fromStringToDate(applicationDate);

        logger.debug("Getting Price from service");
        return pricesService.getPriceByBrandProductAndDate(brandId, productId, parsedApplicationDate);
    }
}