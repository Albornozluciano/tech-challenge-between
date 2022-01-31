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

    /**
     * Converts String type to Date type.
     * @param stringDate - String that represents a date with pattern yyyy-MM-dd-HH.mm.ss
     * @return A Date with pattern yyyy-MM-dd-HH.mm.ss.
     **/
    public static Date fromStringToDate(String stringDate) throws CustomException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        Date dateParsed;
        try {
            dateParsed = sdf.parse(stringDate);
        } catch (ParseException e) {
            logger.error("Error parsing string to date: " + stringDate);
            throw new CustomException(new ApiError(UNKNOWN_INTERNAL_SERVER_ERROR, "Error parsing string to date value: " + stringDate + "."), e);
        } catch (NullPointerException npe) {
            logger.error("Error parsing null string to date");
            throw new CustomException(new ApiError(UNKNOWN_INTERNAL_SERVER_ERROR, "Error parsing null string to date"), npe);
        }
        return dateParsed;
    }

    /**
     * Converts Date type to String type.
     * @param date - Date with pattern yyyy-MM-dd-HH.mm.ss
     * @return A String that represents the date with pattern yyyy-MM-dd-HH.mm.ss.
     **/
    public static String fromDateToString(Date date) throws CustomException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        String stringDate;
        try {
            stringDate = sdf.format(date);
        } catch (NullPointerException npe) {
            logger.error("Error parsing null date to string");
            throw new CustomException(new ApiError(UNKNOWN_INTERNAL_SERVER_ERROR, "Error parsing null date to string"), npe);
        }
        return stringDate;
    }
}
