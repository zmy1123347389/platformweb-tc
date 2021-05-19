package com.behere.common.utils;

import java.util.UUID;

/**
 * @author behere
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    public static String getRandomNumber() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 1000));
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSixRandomNumber() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }

}
