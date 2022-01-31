package com.between.techchallenge.util;

import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DateUtilTest {

    @Test
    public void convertValidDateToString() throws CustomException {
        Date date = new Date();

        String stringDate = DateUtils.fromDateToString(date);
        Date newDate = DateUtils.fromStringToDate(stringDate);

        assertEquals(newDate.getYear(), date.getYear());
        assertEquals(newDate.getMonth(), date.getMonth());
        assertEquals(newDate.getDay(), date.getDay());
        assertEquals(newDate.getHours(), date.getHours());
        assertEquals(newDate.getMinutes(), date.getMinutes());
        assertEquals(newDate.getSeconds(), date.getSeconds());
    }

    @Test
    public void convertValidStringToDate() throws CustomException {
        String validStringDate = "1999-02-27-10.20.30";
        Date newDate = DateUtils.fromStringToDate(validStringDate);
        String newStringDate = DateUtils.fromDateToString(newDate);

        assertEquals(newStringDate, validStringDate);
    }

    @Test
    public void convertInvalidStringToDate() {
        String invalidStringDate = "123";
        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            DateUtils.fromStringToDate(invalidStringDate);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.UNKNOWN_INTERNAL_SERVER_ERROR.getType(), thrown.getApiError().getType());
        assertEquals("Error parsing string to date value: " + invalidStringDate + ".", thrown.getApiError().getDetail());
    }
    @Test
    public void convertNullStringToDate() {
        String invalidStringDate = null;
        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            DateUtils.fromStringToDate(invalidStringDate);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.UNKNOWN_INTERNAL_SERVER_ERROR.getType(), thrown.getApiError().getType());
        assertEquals("Error parsing null string to date", thrown.getApiError().getDetail());
    }

    @Test
    public void convertNullDateToString() {
        Date date = null;
        CustomException thrown = Assertions.assertThrows(CustomException.class, () -> {
            DateUtils.fromDateToString(date);
        });
        assertNotNull(thrown);
        assertEquals(ApiError.ValidationError.UNKNOWN_INTERNAL_SERVER_ERROR.getType(), thrown.getApiError().getType());
        assertEquals("Error parsing null date to string", thrown.getApiError().getDetail());
    }

}