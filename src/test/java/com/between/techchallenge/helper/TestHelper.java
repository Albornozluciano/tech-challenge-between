package com.between.techchallenge.helper;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.dto.PricingDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class TestHelper {
    protected String baseUrl = "";
    protected Map<String, String> queryParams = null;
    protected final Integer defaultValidProductId = 35455;
    protected final Integer defaultValidBrandId = 1;
    protected final String defaultValidApplicationDate = "2020-06-14-16.00.00";

    protected String defaultCurrencyId = "EUR";
    protected Integer defaultPVPId = 1;
    protected Double defaultPVPTotal = 0.0;
    protected Integer defaultFeeId = 1;
    protected Double defaultFeeTotal = 0.0;
    protected String defaultApplicationFrom = "2020-06-14-00.00.00";
    protected String defaultApplicationTo = "2020-12-31-23.59.59";

    protected final String PRODUCT_ID_PARAM_NAME = "productId";
    protected final String BRAND_ID_PARAM_NAME = "brandId";
    protected final String APPLICATION_DATE_PARAM_NAME = "applicationDate";

    protected PricingDTO.PricingDTOBuilder pricingDTOBuilder;
    protected BrandProductPriceDTO.BrandProductPriceDTOBuilder brandProductPriceDTOBuilder;

    protected final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    protected String buildUrl(String baseUrl, Map<String, String> queryParams) {
        StringBuilder queryParamsString = new StringBuilder("?");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            queryParamsString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return baseUrl+queryParamsString;
    }
}
