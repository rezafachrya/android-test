package com.example.bnilist.utils;

import android.content.Context;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilHelper {

    public static String getCurrentTimeStamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    public static String thousandFormatNumber(String number){
        Double f = 0.0;
        if(number!=null && !number.equals("null")) {
            f = Double.parseDouble(number);
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(f).replace(",",".");
    }

    public static String capitalize (String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String capitalizeFully(String str) {
        StringBuilder sb = new StringBuilder();
        boolean cnl = true; // <-- capitalize next letter.
        for (char c : str.toCharArray()) {
            if (cnl && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                cnl = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
            if (Character.isWhitespace(c)) {
                cnl = true;
            }
        }
        return sb.toString();
    }
}
