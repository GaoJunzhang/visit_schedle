package com.zgj.sc.scheduled.common;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class VTools {
    private static String SEPARATOR_OF_MAC = ":";
    public static String randomMac4Qemu(){
        Random random = new Random();
        String[] mac = {
                String.format("%02x", 0x52),
                String.format("%02x", 0x54),
                String.format("%02x", 0x00),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(SEPARATOR_OF_MAC, mac);
    }
    public static String timestamp2Str(Timestamp timestamp,String formate){
        if (StringUtils.isEmpty(formate)){
            formate = "yyyy-MM-dd HH:mm:ss";
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat(formate);
        try {
            //方法一
            tsStr = sdf.format(ts);
            System.out.println(tsStr);
/*            //方法二
            tsStr = ts.toString();
            System.out.println(tsStr);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
