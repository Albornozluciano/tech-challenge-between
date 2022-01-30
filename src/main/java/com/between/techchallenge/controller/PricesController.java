package com.between.techchallenge.controller;

import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.validator.RequestFieldsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricesController {
    Logger logger = LoggerFactory.getLogger(PricesController.class);

    @GetMapping("/prices")
    public ResponseEntity<String> getPriceByProductAndBrand(
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String applicationDate
    ) throws CustomException {
        RequestFieldsValidator.validatePathParams(productId, brandId, applicationDate);
        return null;
    }
}
