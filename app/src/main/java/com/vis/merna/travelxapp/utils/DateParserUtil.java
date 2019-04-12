package com.vis.merna.travelxapp.utils;

import java.text.ParseException;
import java.util.Date;

public class DateParserUtil {
    public static Long parseStringDateToLong(String date) {

        try {
            Date dDate = Constants.simpleDateFormat.parse(date);
            return dDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String[] parseLongDateToStrings(Long lDate) {
        Date date = new Date();
        if(lDate != null) {
            date.setTime(lDate);
        }
        return Constants.simpleDateFormat.format(date).split(" ");
    }
}
