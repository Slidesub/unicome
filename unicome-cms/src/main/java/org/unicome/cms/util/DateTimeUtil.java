package org.unicome.cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static final String yyyyMMdd = "yyyy-MM-dd";

    public static Date parse(String source, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return sdf.parse(source);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return sdf.format(date);
    }

    public static Date dateCal(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
    public static void main(String[] args) {
        String date = "2018-12-31";
        try {
            Date newDate = dateCal(parse(date, yyyyMMdd), -15);
            System.out.println(format(newDate, yyyyMMdd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
