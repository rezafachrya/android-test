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
    public static int imgSeq = 0;
    public static String getAndroidID(Context c){
        StringBuilder sb = new StringBuilder();
        sb.append(android.os.Build.MANUFACTURER);
        sb.append(" ");
        sb.append(android.os.Build.MODEL);
        sb.append(" ");
        sb.append(android.os.Build.SERIAL);
        String unique = sb.toString();

        return md5(unique);
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
            return s;
        }
    }

    public static String getCurrentTimeStamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    public static String addPaddingToString(String t, String s, int num) {
        String newString = "";
        for(int i=0;i<(s.length()/4);i++){
            newString = newString + s.substring((i*4),((i+1)*4)) + " ";
        }

        return newString;

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return (matcher.matches() && password.length()>=8);

    }

    public static String convertDate(String oDate, String pattern){
        //String date="Mar 10, 2016 6:30:00 PM";
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd");
        Date newDate= null;
        try {
            newDate = spf.parse(oDate);
            spf= new SimpleDateFormat(pattern);
            return spf.format(newDate);
        } catch (ParseException e) {
            return "-";
            //e.printStackTrace();
        }

    }

    public static String thousandFormatNumberReversedMin(float number){
        return String.format("%,.0f",number).replace(",",".").replace("-","") + (number<0?"(-)":"");
    }

    public static String thousandFormatNumberReversedMin(String number){
        Double f = 0.0;
        String oldNumber = number;
        if(oldNumber!=null && !oldNumber.equals("null")) {
            if (oldNumber.charAt(oldNumber.length() - 1) == '-') {
                f = Double.parseDouble(number.substring(0, number.length() - 1))*-1;
            } else {
                f = Double.parseDouble(number);
            }
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(f).replace(",",".").replace("-","") + (f<0?"(-)":"");
    }


    public static String thousandFormatNumber(String number){
        Double f = 0.0;
        if(number!=null && !number.equals("null")) {
            f = Double.parseDouble(number);
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(f).replace(",",".");
    }

    public static String thousandFormatNumber(float number){
        return String.format("%,.0f",number).replace(",",".");
    }

    public static String phonenNumberMasking(String number){
        if(number!=null) {
            if (number.length() == 3) {
                return number;
            } else if (number.length() > 3) {
                String maskedNumber = number.substring(number.length() - 3);
                for (int i = 0; i < number.length() - 3; i++) {
                    maskedNumber = "*" + maskedNumber;
                }
                return maskedNumber;
            } else {
                // whatever is appropriate in this case
                return "************";
            }
        }else{
            return "************";
        }
    }


    public static boolean checkLast6Month(String tgltrx, boolean isReqCicilan) {
        boolean stat = false;
        int tahunPar = 0;
        int bulanPar = 0;
        int hariPar = 0;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(date));
        String currentDate = dateFormat.format(date);
        int hariCurr = Integer.parseInt(currentDate.substring(0,2));
        int bulanCurr = Integer.parseInt(currentDate.substring(3,5));
        int tahunCurr = Integer.parseInt(currentDate.substring(6,10));

        if (isReqCicilan) {
             tahunPar = Integer.parseInt(tgltrx.substring(6, 10).trim());
             bulanPar = Integer.parseInt(tgltrx.substring(3, 5).trim());
             hariPar = Integer.parseInt(tgltrx.substring(0, 2).trim());

        } else {
             tahunPar = Integer.parseInt(tgltrx.substring(0,4).trim());
             bulanPar = Integer.parseInt(tgltrx.substring(5,7).trim());
             hariPar = Integer.parseInt(tgltrx.substring(8,10).trim());
        }
        Log.d("Tahun-Bulan-Hari : ", String.valueOf(tahunPar).toString() + "-" + String.valueOf(bulanPar) +"-" + String.valueOf(hariPar) );


        if (tahunPar > tahunCurr ) {
            return stat;
        } else if (tahunPar == tahunCurr ) {
            if ((bulanCurr - bulanPar) > 6) {
                return stat;
            } else if ((bulanCurr - bulanPar) > 5) {
                if (hariPar > hariCurr) {
                    return stat;
                }
            } else if (bulanPar > bulanCurr) {
                return stat;
            } else if (hariPar > hariCurr) {
                return stat;
            }


        } else if (tahunCurr - tahunPar > 1  ) {
            return stat;
        } else if (tahunCurr - tahunPar > 0  ) {

             if ((((12 - bulanPar) + bulanCurr)) > 6) {
                return stat;
             } else if ((((12 - bulanPar) + bulanCurr)) > 5) {
                if (hariPar < hariCurr) {
                    return stat;
                }
            }
        }
        stat = true;
        return stat ;
    }

    public static boolean checkLast12Month(String openDate) {
        boolean stat = false;
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(date));
        String currentDate = dateFormat.format(date);
        int hariCurr = Integer.parseInt(currentDate.substring(0,2));
        int bulanCurr = Integer.parseInt(currentDate.substring(3,5));
        int tahunCurr = Integer.parseInt(currentDate.substring(6,10));

        int tahunPar = Integer.parseInt(openDate.substring(0,4).trim());
        int bulanPar = Integer.parseInt(openDate.substring(5,7).trim());
        int hariPar  = Integer.parseInt(openDate.substring(8,10).trim());

        Log.d("Tahun-Bulan-Hari : ", String.valueOf(tahunPar).toString() + "-" + String.valueOf(bulanPar) +"-" + String.valueOf(hariPar) );

        if (tahunCurr - tahunPar> 1) {
             stat = true;
        } else if (tahunCurr - tahunPar > 0) {
            if (bulanPar < bulanCurr ) {
                stat = true;
            } else if (bulanCurr == bulanPar) {
                if (hariPar < hariCurr) {
                    stat = true;

                }
            }

        }
        return stat ;
    }
}
