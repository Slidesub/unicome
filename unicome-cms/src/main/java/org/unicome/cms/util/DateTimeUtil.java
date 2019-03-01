package org.unicome.cms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
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
//            List<String> a = new ArrayList<>();
////            a.add("cc");
////            Long b= 12L;
////            log.info("" + b);
////
////            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
////            Calendar cal = Calendar.getInstance();
////            System.out.println(cal.getTime());

//            String a = "%7B%22p28Data%22%3A%5B%7B%22FileName%22%3A%22iTermination_DeleteM.txt%22%2C%22employee%22%3A%22%C3%AF%C2%BB%C2%BF20181231%23Z7%2320181231%2388869407%2311033789%23Mr.+%C3%A6%C2%9D%C2%A8%C3%A5%C2%92%C2%8C%C3%A9%C2%87%C2%91%2FYANG+Hejin%23RBAC+CC%23CC-AT%2FMFO1-Szh3%23Reg.+CN+Workshop+Dir%23SZH4%2320150301%2310709486%2300%23CN%2318013483353%23256320%5Cr%5Cn20181231%23Z7%2320181231%2389200341%2311270918%23Ms.+%C3%A5%C2%BC%C2%A0%C3%A6%C2%98%C2%8E%C3%A6%C2%97%C2%AD%2FZHANG+Mingxu%23TTCB%23TT%2FSCN-SOP21%23Leasing+Staff%23PK10%2300000000%2311487093%2300%23CN%23305672%5Cr%5Cn20181231%23Z7%2320181231%2389227251%2311290405%23Mr.+%C3%A8%C2%B5%C2%B5%C3%A5%C2%9F%C2%B9%2FZHAO+Kerwin%23RBAC+CC%23CC-AT%2FMFE1-Szh3%23Regular+CN+Staff%23SZH4%2320170420%2310709486%2300%23CN%2315150890612%23256320%5Cr%5Cn%22%7D%5D%7D=";
//            String b = URLDecoder.decode(a, "UTF-8");
//            System.out.println(b);
//
//            String c = "你好";
//            String d = URLEncoder.encode(c , "UTF-8");
//            String e = URLDecoder.decode(d, "UTF-8");
//            System.out.println(d);
//            System.out.println(e);
            Object o = false;
            boolean isRight = (Boolean)o;
            System.out.println(isRight);

            List<String> a = new ArrayList<String>();
            a.add("a");
//            a.add("b");
            System.out.println(a.toString().replace("[", "(").replace("]", ")"));
        }catch(Exception e) {
            e.printStackTrace();
        }


    }
}
