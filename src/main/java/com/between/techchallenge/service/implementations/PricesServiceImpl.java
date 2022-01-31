package com.between.techchallenge.service.implementations;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.model.Price;
import com.between.techchallenge.repository.PricesRepository;
import com.between.techchallenge.service.PricesService;
import com.between.techchallenge.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import static com.between.techchallenge.error.ApiError.ValidationError.BD_INTERNAL_SERVER_ERROR;
import static com.between.techchallenge.error.ApiError.ValidationError.PRICE_NOT_FOUND;

import java.util.Date;

@Service
public class PricesServiceImpl implements PricesService {
    Logger logger = LoggerFactory.getLogger(PricesServiceImpl.class);

    @Autowired
    PricesRepository priceRepository;

    @Override
    public BrandProductPriceDTO getPriceByBrandProductAndDate(String brandId, String productId, Date applicationDate) throws CustomException {
        Price price;
        try {
            logger.debug("Getting Price from repository");
            price = priceRepository.getPricesByBrandProductAndDate(brandId, productId, DateUtils.fromDateToString(applicationDate));
        } catch (DataAccessException dax) {
            logger.debug("Error getting price from database");
            throw new CustomException(new ApiError(BD_INTERNAL_SERVER_ERROR, "Error getting price from database."), dax);
        } catch (Throwable t) {
            logger.debug("Error getting price from database");
            throw new CustomException(new ApiError(BD_INTERNAL_SERVER_ERROR, "Unkown error getting price from database."), t);
        }

        if (price == null) {
            logger.debug("Price not found");
            throw new CustomException(new ApiError(PRICE_NOT_FOUND, "Price error not found with values BrandId: '" + brandId + "',  ProductId: '" + productId + "', ApplicationDate: '" + DateUtils.fromDateToString(applicationDate) + "'."));
        }

        logger.debug("Building and retrieving price: " + price);
        return new BrandProductPriceDTO().buildFrom(price);
    }
}
