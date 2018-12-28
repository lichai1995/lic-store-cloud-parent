package com.lic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chai
 * time 2018/04/22 16:03
 */
public class DateUtil {
    public static String date2Str(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
