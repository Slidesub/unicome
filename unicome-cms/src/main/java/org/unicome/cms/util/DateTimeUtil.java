package org.unicome.cms.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
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
//        String date = "2018-12-31";
//        try {
//            Date newDate = dateCal(parse(date, yyyyMMdd), -15);
//            System.out.println(format(newDate, yyyyMMdd));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        try {
            List<String> a = new ArrayList<>();
            a.add("cc");
            Long b= 12L;
            log.info("" + b);

            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
            Calendar cal = Calendar.getInstance();
            System.out.println(cal.getTime());
        }catch(Exception e) {
            e.printStackTrace();
        }



    }
}
