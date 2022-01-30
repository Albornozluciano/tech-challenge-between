package com.between.techchallenge.util;

import com.between.techchallenge.controller.PricesController;
import com.between.techchallenge.error.ApiError;
import com.between.techchallenge.error.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.between.techchallenge.error.ApiError.ValidationError.UNKNOWN_INTERNAL_SERVER_ERROR;

public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    private static final Logger logger = LoggerFactory.getLogger(PricesController.class);

    public static Date fromStringToDate(String stringDate) throws CustomException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        Date dateParsed;
        try {
            dateParsed = sdf.parse(stringDate);
        } catch (ParseException e) {
            logger.error("Error parsing string to date: " + stringDate);
            throw new CustomException(new ApiError(UNKNOWN_INTERNAL_SERVER_ERROR, "Error parsing string to date value: " + stringDate + "."));
        }
        return dateParsed;
    }

    public static String fromDateToString(Date date) {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        return sdf.format(date);
    }
}
