package com.app.amakenchatui.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jai on 20/02/16.
 */
public class ACDate {
    private static final String TAG = "VCDate";
    public static int WELCOME_USER_CARD_FORMAT_INDEX = 0;
    public static int PROMOTIONS_FORMAT_INDEX = 1;
    public static String WELCOME_USER_CARD_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS";
    public static String PROMOTIONS_FORMAT = "MM/dd/yyyy hh:mm:ss aaa";
    public static String TO_FORMAT = "dd MMM, yy hh:mm aaa";

    /**
     * Get the date from string in required format
     *
     * @param dateString The date in string
     * @return The formatted string
     */
    public static Date getDateFromString(String dateString, int format) {
        if (format == WELCOME_USER_CARD_FORMAT_INDEX) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WELCOME_USER_CARD_FORMAT, Locale.ENGLISH);
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }
        } else if (format == PROMOTIONS_FORMAT_INDEX) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PROMOTIONS_FORMAT, Locale.ENGLISH);
            try {
                return simpleDateFormat.parse(  dateString);
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return null;
    }

    public static String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TO_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public static String getFormattedDateString(String dateString, int format) {
        Date date = getDateFromString(dateString, format);
        String outputDateString = getStringFromDate(date);
        return outputDateString;
    }
}