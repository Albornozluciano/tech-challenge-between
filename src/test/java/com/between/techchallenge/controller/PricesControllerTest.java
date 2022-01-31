package com.between.techchallenge.controller;

import static com.between.techchallenge.error.ApiError.ValidationError.REQUIRED_PARAM;
import static com.between.techchallenge.error.ApiError.ValidationError.TYPE_PARAM;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.dto.BrandProductPriceDTO.BrandProductPriceDTOBuilder;
import com.between.techchallenge.dto.FeeDTO;
import com.between.techchallenge.dto.PVPDTO;
import com.between.techchallenge.dto.PricingDTO.PricingDTOBuilder;
import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest extends TestHelper {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        initializeValues();
    }

    // Challenge tests
    @Test
    public void challengeTest1() throws Exception {
        // Set application date query param to test
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "2020-06-14-10.00.00");

        // Prepare expected results
        Integer expectedPVPId = 1;
        Double expectedPVPValue = 35.50;
        Integer expectedFeeId = 1;
        Double expectedFeeValue = 10.0;
        String expectedApplicationFrom = "2020-06-14-00.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = brandProductPriceDTOBuilder
            .withPricingBuilder(
                pricingDTOBuilder
                        .withPerceivedValue(expectedPVPId, expectedPVPValue)
                        .withFee(expectedFeeId, expectedFeeValue)
                        .withApplicationFrom(expectedApplicationFrom)
                        .withApplicationTo(expectedApplicationTo)
            )
            .build();

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void challengeTest2() throws Exception {
        // Set application date query param to test
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "2020-06-14-16.00.00");

        // Prepare expected results
        Integer expectedPVPId = 2;
        Double expectedPVPValue = 25.45;
        Integer expectedFeeId = 2;
        Double expectedFeeValue = 20.0;
        String expectedApplicationFrom = "2020-06-14-15.00.00";
        String expectedApplicationTo = "2020-06-14-18.30.00";

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = brandProductPriceDTOBuilder
            .withPricingBuilder(
                pricingDTOBuilder
                        .withPerceivedValue(expectedPVPId, expectedPVPValue)
                        .withFee(expectedFeeId, expectedFeeValue)
                        .withApplicationFrom(expectedApplicationFrom)
                        .withApplicationTo(expectedApplicationTo)
            )
            .build();

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void challengeTest3() throws Exception {
        // Set application date query param to test
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "2020-06-14-21.00.00");

        // Prepare expected results
        Integer expectedPVPId = 1;
        Double expectedPVPValue = 35.50;
        Integer expectedFeeId = 1;
        Double expectedFeeValue = 10.0;
        String expectedApplicationFrom = "2020-06-14-00.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = brandProductPriceDTOBuilder
                .withPricingBuilder(
                        pricingDTOBuilder
                                .withPerceivedValue(expectedPVPId, expectedPVPValue)
                                .withFee(expectedFeeId, expectedFeeValue)
                                .withApplicationFrom(expectedApplicationFrom)
                                .withApplicationTo(expectedApplicationTo)
                )
                .build();

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void challengeTest4() throws Exception {
        // Set application date query param to test
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "2020-06-15-10.00.00");

        // Prepare expected results
        Integer expectedPVPId = 3;
        Double expectedPVPValue = 30.50;
        Integer expectedFeeId = 3;
        Double expectedFeeValue = 30.0;
        String expectedApplicationFrom = "2020-06-15-00.00.00";
        String expectedApplicationTo = "2020-06-15-11.00.00";

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = brandProductPriceDTOBuilder
                .withPricingBuilder(
                        pricingDTOBuilder
                                .withPerceivedValue(expectedPVPId, expectedPVPValue)
                                .withFee(expectedFeeId, expectedFeeValue)
                                .withApplicationFrom(expectedApplicationFrom)
                                .withApplicationTo(expectedApplicationTo)
                )
                .build();

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void challengeTest5() throws Exception {
        // Set application date query param to test
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "2020-06-16-21.00.00");

        // Prepare expected results
        Integer expectedPVPId = 4;
        Double expectedPVPValue = 38.95;
        Integer expectedFeeId = 4;
        Double expectedFeeValue = 40.0;
        String expectedApplicationFrom = "2020-06-15-16.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = brandProductPriceDTOBuilder
                .withPricingBuilder(
                        pricingDTOBuilder
                                .withPerceivedValue(expectedPVPId, expectedPVPValue)
                                .withFee(expectedFeeId, expectedFeeValue)
                                .withApplicationFrom(expectedApplicationFrom)
                                .withApplicationTo(expectedApplicationTo)
                )
                .build();

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    // Own tests

    @Test
    public void getPriceValidParamsShouldBeOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getPriceWithoutBrandShouldBeBadRequest() throws Exception {
        queryParams.put(BRAND_ID_PARAM_NAME, "");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.put(BRAND_ID_PARAM_NAME, " ");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.remove(BRAND_ID_PARAM_NAME);

        ApiError expectedApiError = new ApiError(REQUIRED_PARAM, "Invalid param. The following param is required but it's missing: '" + BRAND_ID_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithoutProductShouldBeBadRequest() throws Exception {
        queryParams.put(PRODUCT_ID_PARAM_NAME, "");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.put(PRODUCT_ID_PARAM_NAME, " ");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.remove(PRODUCT_ID_PARAM_NAME);

        ApiError expectedApiError = new ApiError(REQUIRED_PARAM, "Invalid param. The following param is required but it's missing: '" + PRODUCT_ID_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithoutApplicationDateShouldBeBadRequest() throws Exception {
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.put(APPLICATION_DATE_PARAM_NAME, " ");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        queryParams.remove(APPLICATION_DATE_PARAM_NAME);

        ApiError expectedApiError = new ApiError(REQUIRED_PARAM, "Invalid param. The following param is required but it's missing: '" + APPLICATION_DATE_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithInvalidApplicationDateTypeShouldBeBadRequest() throws Exception {
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "1234");

        ApiError expectedApiError = new ApiError(TYPE_PARAM, "Invalid param. Param type expected: 'date' and format 'yyyy-MM-dd-HH.mm.ss'. Param: '" + APPLICATION_DATE_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));

        queryParams.put(APPLICATION_DATE_PARAM_NAME, "invalidPattern");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));

        queryParams.put(APPLICATION_DATE_PARAM_NAME, "21/02/1999");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));

        queryParams.put(APPLICATION_DATE_PARAM_NAME, "1999-21-02-12.00.00.00");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithInvalidBrandTypeShouldBeBadRequest() throws Exception {
        queryParams.put(BRAND_ID_PARAM_NAME, "notANumber");

        ApiError expectedApiError = new ApiError(TYPE_PARAM, "Invalid param. Param type expected: 'long'. Param: '" + BRAND_ID_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));

        queryParams.put(BRAND_ID_PARAM_NAME, "1.0");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithInvalidProductTypeShouldBeBadRequest() throws Exception {
        queryParams.put(PRODUCT_ID_PARAM_NAME, "notANumber");

        ApiError expectedApiError = new ApiError(TYPE_PARAM, "Invalid param. Param type expected: 'long'. Param: '" + PRODUCT_ID_PARAM_NAME + "'.");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));

        queryParams.put(PRODUCT_ID_PARAM_NAME, "1.0");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo(gson.toJson(expectedApiError))));
    }

    @Test
    public void getPriceWithNonExistentProductShouldBeNotFound() throws Exception {
        queryParams.put(PRODUCT_ID_PARAM_NAME, "12345");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPriceWithNonExistentBrandShouldBeNotFound() throws Exception {
        queryParams.put(BRAND_ID_PARAM_NAME, "12345");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPriceWithNonExistentApplicationDateShouldBeNotFound() throws Exception {
        queryParams.put(APPLICATION_DATE_PARAM_NAME, "1990-01-01-00.00.00");

        mvc.perform(MockMvcRequestBuilders.get(buildUrl(baseUrl, queryParams)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private void initializeValues() {
        baseUrl = "/v1/prices";

        queryParams = new HashMap<>();
        queryParams.put(PRODUCT_ID_PARAM_NAME, defaultValidProductId.toString());
        queryParams.put(BRAND_ID_PARAM_NAME, defaultValidBrandId.toString());
        queryParams.put(APPLICATION_DATE_PARAM_NAME, defaultValidApplicationDate);

        pricingDTOBuilder = new PricingDTOBuilder()
                .withApplicationTo(defaultApplicationTo)
                .withApplicationFrom(defaultApplicationFrom)
                .withFee(
                        new FeeDTO(
                                defaultFeeId,
                                defaultFeeTotal
                        )
                )
                .withPerceivedValue(
                        new PVPDTO(
                                defaultPVPId,
                                defaultPVPTotal
                        )
                )
                .withCurrencyId(defaultCurrencyId);

        brandProductPriceDTOBuilder = new BrandProductPriceDTOBuilder()
                .withBrandId(defaultValidBrandId)
                .withProductId(defaultValidProductId)
                .withPricingBuilder(pricingDTOBuilder);
    }
}