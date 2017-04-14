package com.framgia.forder.utils;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Duong on 4/14/2017.
 */

public class Utils {
    public static class DateTimeUntils {
        public static String convertUiFormatToDataFormat(String time, String inputFormat,
                String outputFormat) {
            if (TextUtils.isEmpty(time)) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
            SimpleDateFormat newSdf = new SimpleDateFormat(outputFormat, Locale.getDefault());
            try {
                return newSdf.format(sdf.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
