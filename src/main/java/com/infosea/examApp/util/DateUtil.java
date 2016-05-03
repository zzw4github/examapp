package com.infosea.examApp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by infosea on 2016/4/20.
 */
public class DateUtil {
    static DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

    public static Date parse(Date date) {
        String str = dateFormat.format(date);
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
