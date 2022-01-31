package com.between.techchallenge.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.between.techchallenge.dto.BrandProductPriceDTO;
import com.between.techchallenge.dto.FeeDTO;
import com.between.techchallenge.dto.PVPDTO;
import com.between.techchallenge.dto.PricingDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String baseUrl = "/v1/prices";
    private final String initialQueryParam = "?productId=PRODUCTID&brandId=BRANDID&applicationDate=APPLICATIONDATE";

    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Test
    public void ChallengeTest1() throws Exception {
        // Prepare expected values
        String expectedCurrencyId = "EUR";

        Integer expectedPVPId = 1;
        Double expectedPVPTotal = 35.50;

        Integer expectedFeeId = 1;
        Double expectedFeeTotal = 10.0;

        String expectedApplicationFrom = "2020-06-14-00.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        Integer expectedProductId = 35455;
        Integer expectedBrandId = 1;

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = new BrandProductPriceDTO(
            expectedProductId,
            expectedBrandId,
            new PricingDTO(
                expectedCurrencyId,
                new PVPDTO(
                    expectedPVPId,
                    expectedPVPTotal
                ),
                new FeeDTO(
                    expectedFeeId,
                    expectedFeeTotal
                ),
                expectedApplicationFrom,
                expectedApplicationTo
            )
        );

        // Prepare query params
        String queryParams = initialQueryParam.replace("PRODUCTID", expectedProductId.toString());
        queryParams = queryParams.replace("BRANDID", expectedBrandId.toString());

        // Test case 1
        String applicationDate = "2020-06-14-10.00.00";
        queryParams = queryParams.replace("APPLICATIONDATE", applicationDate);

        // Prepare url
        String url = baseUrl+queryParams;

        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void ChallengeTest2() throws Exception {
        // Prepare expected values
        String expectedCurrencyId = "EUR";

        Integer expectedPVPId = 2;
        Double expectedPVPTotal = 25.45;

        Integer expectedFeeId = 2;
        Double expectedFeeTotal = 20.0;

        String expectedApplicationFrom = "2020-06-14-15.00.00";
        String expectedApplicationTo = "2020-06-14-18.30.00";

        Integer expectedProductId = 35455;
        Integer expectedBrandId = 1;

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = new BrandProductPriceDTO(
                expectedProductId,
                expectedBrandId,
                new PricingDTO(
                        expectedCurrencyId,
                        new PVPDTO(
                                expectedPVPId,
                                expectedPVPTotal
                        ),
                        new FeeDTO(
                                expectedFeeId,
                                expectedFeeTotal
                        ),
                        expectedApplicationFrom,
                        expectedApplicationTo
                )
        );

        // Prepare query params
        String queryParams = initialQueryParam.replace("PRODUCTID", expectedProductId.toString());
        queryParams = queryParams.replace("BRANDID", expectedBrandId.toString());

        // Test case 2
        String applicationDate = "2020-06-14-16.00.00";
        queryParams = queryParams.replace("APPLICATIONDATE", applicationDate);

        // Prepare url
        String url = baseUrl+queryParams;

        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void ChallengeTest3() throws Exception {
        // Prepare expected values
        String expectedCurrencyId = "EUR";

        Integer expectedPVPId = 1;
        Double expectedPVPTotal = 35.50;

        Integer expectedFeeId = 1;
        Double expectedFeeTotal = 10.0;

        String expectedApplicationFrom = "2020-06-14-00.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        Integer expectedProductId = 35455;
        Integer expectedBrandId = 1;

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = new BrandProductPriceDTO(
                expectedProductId,
                expectedBrandId,
                new PricingDTO(
                        expectedCurrencyId,
                        new PVPDTO(
                                expectedPVPId,
                                expectedPVPTotal
                        ),
                        new FeeDTO(
                                expectedFeeId,
                                expectedFeeTotal
                        ),
                        expectedApplicationFrom,
                        expectedApplicationTo
                )
        );

        // Prepare query params
        String queryParams = initialQueryParam.replace("PRODUCTID", expectedProductId.toString());
        queryParams = queryParams.replace("BRANDID", expectedBrandId.toString());

        // Test case 3
        String applicationDate = "2020-06-14-21.00.00";
        queryParams = queryParams.replace("APPLICATIONDATE", applicationDate);

        // Prepare url
        String url = baseUrl+queryParams;

        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void ChallengeTest4() throws Exception {
        // Prepare expected values
        String expectedCurrencyId = "EUR";

        Integer expectedPVPId = 3;
        Double expectedPVPTotal = 30.50;

        Integer expectedFeeId = 3;
        Double expectedFeeTotal = 30.0;

        String expectedApplicationFrom = "2020-06-15-00.00.00";
        String expectedApplicationTo = "2020-06-15-11.00.00";

        Integer expectedProductId = 35455;
        Integer expectedBrandId = 1;

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = new BrandProductPriceDTO(
                expectedProductId,
                expectedBrandId,
                new PricingDTO(
                        expectedCurrencyId,
                        new PVPDTO(
                                expectedPVPId,
                                expectedPVPTotal
                        ),
                        new FeeDTO(
                                expectedFeeId,
                                expectedFeeTotal
                        ),
                        expectedApplicationFrom,
                        expectedApplicationTo
                )
        );

        // Prepare query params
        String queryParams = initialQueryParam.replace("PRODUCTID", expectedProductId.toString());
        queryParams = queryParams.replace("BRANDID", expectedBrandId.toString());

        // Test case 4
        String applicationDate = "2020-06-15-10.00.00";
        queryParams = queryParams.replace("APPLICATIONDATE", applicationDate);

        // Prepare url
        String url = baseUrl+queryParams;

        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

    @Test
    public void ChallengeTest5() throws Exception {
        // Prepare expected values
        String expectedCurrencyId = "EUR";

        Integer expectedPVPId = 4;
        Double expectedPVPTotal = 38.95;

        Integer expectedFeeId = 4;
        Double expectedFeeTotal = 40.0;

        String expectedApplicationFrom = "2020-06-15-16.00.00";
        String expectedApplicationTo = "2020-12-31-23.59.59";

        Integer expectedProductId = 35455;
        Integer expectedBrandId = 1;

        // Build expected object
        BrandProductPriceDTO expectedBrandProductPrice = new BrandProductPriceDTO(
                expectedProductId,
                expectedBrandId,
                new PricingDTO(
                        expectedCurrencyId,
                        new PVPDTO(
                                expectedPVPId,
                                expectedPVPTotal
                        ),
                        new FeeDTO(
                                expectedFeeId,
                                expectedFeeTotal
                        ),
                        expectedApplicationFrom,
                        expectedApplicationTo
                )
        );

        // Prepare query params
        String queryParams = initialQueryParam.replace("PRODUCTID", expectedProductId.toString());
        queryParams = queryParams.replace("BRANDID", expectedBrandId.toString());

        // Test case 5
        String applicationDate = "2020-06-16-21.00.00";
        queryParams = queryParams.replace("APPLICATIONDATE", applicationDate);

        // Prepare url
        String url = baseUrl+queryParams;

        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(gson.toJson(expectedBrandProductPrice))));
    }

}