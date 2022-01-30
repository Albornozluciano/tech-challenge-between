package com.between.techchallenge.validator;

import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.between.techchallenge.error.ApiError.ValidationError.REQUIRED_PARAM;
import static com.between.techchallenge.error.ApiError.ValidationError.TYPE_PARAM;

public class RequestFieldsValidator {

    public static final String PRODUCT_ID_PARAM_KEY = "productId";
    public static final String BRAND_ID_PARAM_KEY = "brandId";
    public static final String APPLICATION_DATE_PARAM_KEY = "applicationDate";

    private static final String LONG_TYPE = "long";
    private static final String DATE_TYPE = "date";
    private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    public static void validatePathParams(String productId, String brandId, String applicationDate) throws CustomException {
        Map<String, ValidationRequirements> params = new HashMap<>();
        params.put(PRODUCT_ID_PARAM_KEY, new ValidationRequirements(productId, true, LONG_TYPE));
        params.put(BRAND_ID_PARAM_KEY, new ValidationRequirements(brandId, true, LONG_TYPE));
        params.put(APPLICATION_DATE_PARAM_KEY, new ValidationRequirements(applicationDate, true, DATE_TYPE));

        for (Map.Entry<String, ValidationRequirements> entry : params.entrySet()) {
            ValidationRequirements requirements = entry.getValue();
            if (requirements.required && requirements.value == null || requirements.value.trim().isEmpty()) {
                throw new CustomException(new ApiError(REQUIRED_PARAM, "Invalid param. The following param is required but it's missing: '" + entry.getKey() + "'."));
            }
            if (!isValidType(requirements.type, requirements.value)) {
                String format = requirements.type.equals(DATE_TYPE)? " and format '" + DATE_FORMAT + "'" : "";
                throw new CustomException(new ApiError(TYPE_PARAM, "Invalid param. Param type expected: '" + requirements.type + "'" + format +". Param: '" + entry.getKey() + "'."));
            }
        }
    }

    private static boolean isValidType(String type, String value) {
        switch(type) {
            case LONG_TYPE:
                try {
                    Long.parseLong(value);
                } catch (NumberFormatException nfe) {
                    return false;
                }
                break;
            case DATE_TYPE:
                DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                sdf.setLenient(false);
                try {
                    sdf.parse(value);
                } catch (ParseException e) {
                    return false;
                }
                break;
        }
        return true;
    }

    private static class ValidationRequirements {
        private final String value;
        private final boolean required;
        private final String type;

        ValidationRequirements(String value, boolean required, String type) {
            this.value = value;
            this.required = required;
            this.type = type;
        }
    }

}
