package com.between.techchallenge.service.implementations;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import com.between.techchallenge.model.Price;
import com.between.techchallenge.repository.PricesRepository;
import com.between.techchallenge.service.PricesService;
import com.between.techchallenge.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.between.techchallenge.error.ApiError.ValidationError.PRICE_NOT_FOUND;

import java.util.Date;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    PricesRepository priceRepository;

    @Override
    public BrandProductPriceDTO getPriceByBrandProductAndDate(String brandId, String productId, Date applicationDate) throws CustomException {
        Price price = priceRepository.getPricesByBrandProductAndDate(brandId, productId, DateUtils.fromDateToString(applicationDate));
        if (price == null) {
            throw new CustomException(new ApiError(PRICE_NOT_FOUND, "Price error not found with values BrandId: '" + brandId + "',  ProductId: '" + productId + "', ApplicationDate: '" + DateUtils.fromDateToString(applicationDate) + "'."));
        }
        return new BrandProductPriceDTO().buildFrom(price);
    }
}
