package com.devian.gamerplacebot.utils;

public class ClubUtils {

    public static boolean isValidId(String number) {
        return number != null && number.matches("\\d{4}");
    }
}
