package com.eriks.growth.Util;

public class StringUtils {

    public static String underscoresToSpaces(String string) {
        if (string.contains("_")) {
            string = string.replaceAll("_", " ");
        }
        return string;
    }

}
