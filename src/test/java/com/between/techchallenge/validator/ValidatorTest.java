package com.between.techchallenge.validator;

import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.between.techchallenge.validator.RequestFieldsValidator.validateRequestParams;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ValidatorTest {
    @Test
    public void requiredParamIsMissing() {
        String validBrandId = "1234";
        String validProductId = "1234";
        String missingDate = null;

        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            validateRequestParams(validBrandId, validProductId, missingDate);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.REQUIRED_PARAM.getType(), thrown.getApiError().getType());
    }

    @Test
    public void invalidParamType() {
        String validBrandId = "1234";
        String validProductId = "1234";
        String invalidDate = "1234";

        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            validateRequestParams(validBrandId, validProductId, invalidDate);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.TYPE_PARAM.getType(), thrown.getApiError().getType());
    }
}