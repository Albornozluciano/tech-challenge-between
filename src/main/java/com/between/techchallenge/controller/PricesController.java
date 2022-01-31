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

/**
 * Prices Controller - Responsible for retrieving product prices and fees.
 **/
@RestController
public class PricesController {
    Logger logger = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    PricesService pricesService;

    /**
     * Retrieves a single price by brand, product and an application date.
     * @param brandId - Brand Identifier
     * @param productId - Product Identifier
     * @param applicationDate - Date with pattern yyyy-mm-dd-hh.MM.ss
     * @return BrandProductPriceDTO with data related to PVP, Fee, DateFrom, DateTo and Currency.
     *  Response status:
     *      200 if there's a valid price for the brand, product and date.
     *      400 if there are incorrect params.
     *      404 if there's not a valid register for the brand, product and date.
     *      500 an unknown internal server error. (DB connection, incorrect date parsing).
     */
    @GetMapping("/v1/prices")
    public BrandProductPriceDTO getPriceByProductAndBrand(
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String applicationDate
    ) throws CustomException {
        logger.debug("Validating request params: " + "ProductId = " + productId + ", BrandId = " + brandId + ", ApplicationDate = " + applicationDate);
        RequestFieldsValidator.validateRequestParams(brandId, productId, applicationDate);

        logger.debug("Parsing ApplicationDate");
        Date parsedApplicationDate = DateUtils.fromStringToDate(applicationDate);

        logger.debug("Getting Price from service");
        return pricesService.getPriceByBrandProductAndDate(brandId, productId, parsedApplicationDate);
    }
}