package com.behere.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Behere
 */
public class CronUtil {

    private static final String ONEC_TIME = "ss mm HH dd MM ?";
    private static final String MONTH_TIME = "ss mm HH dd * ?";
    private static final String DAY_TIME = "ss mm HH * * ?";
    static SimpleDateFormat ONEC_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat YEAR_TIME_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
    static SimpleDateFormat MONTH_TIME_FORMAT = new SimpleDateFormat("dd HH:mm:ss");
    static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static String transationDate2Cron(int type, String dateStr) {
        try {
            if (type == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat(ONEC_TIME);
                return sdf.format(ONEC_TIME_FORMAT.parse(dateStr));
            } else if (type == 1) {
                SimpleDateFormat sdf = new SimpleDateFormat(ONEC_TIME);
                return sdf.format(YEAR_TIME_FORMAT.parse(dateStr));
            } else if (type == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat(MONTH_TIME);
                return sdf.format(MONTH_TIME_FORMAT.parse(dateStr));
            } else if (type == 3) {
                SimpleDateFormat sdf = new SimpleDateFormat(DAY_TIME);
                return sdf.format(DAY_FORMAT.parse(dateStr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        try {
            String str = "13:54:00";
            System.out.println(transationDate2Cron(4, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}