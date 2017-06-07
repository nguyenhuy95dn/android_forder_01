package com.framgia.forder.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.framgia.forder.utils.Constant.NUMBER_COMPRESS;

/**
 * Created by Duong on 4/14/2017.
 */

public class Utils {
    public static final String INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String OUTPUT_TIME_FORMAT = "HH:mm";
    public static final String OUTPUT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String FORMAT_PRICE = "%1$,.0f";
    public static final String TWO_DOT = ":";
    public static final int WRONG_TIME = 11;
    public static final int HOUR_OF_DAY = 24;

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
                return null;
            }
        }

        public static boolean isProductTimeOut(String start, String end) {
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY) + WRONG_TIME;
            int minute = now.get(Calendar.MINUTE);
            if (hour >= 24) {
                hour = hour - HOUR_OF_DAY;
            }
            Date currentHour = convertStringToDate(hour + TWO_DOT + minute);
            Date startHour = convertStringToDate(start);
            Date endHour = convertStringToDate(end);
            return !(startHour.before(currentHour) && endHour.after(currentHour));
        }

        private static Date convertStringToDate(String date) {
            SimpleDateFormat parser =
                    new SimpleDateFormat(Constant.FORMAT_TIME, Locale.getDefault());
            try {
                return parser.parse(date);
            } catch (java.text.ParseException e) {
                return new Date();
            }
        }

        public static String convertDateToString(Date date) {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(Constant.FORMAT_DATE, Locale.getDefault());
            return simpleDateFormat.format(date);
        }
    }

    public static class ImageUtils {
        public static String convertImagetoBase64(InputStream imageStream) {
            final Bitmap image = BitmapFactory.decodeStream(imageStream);
            String encodedImage = encodeImage(image);
            return encodedImage;
        }

        private static String encodeImage(Bitmap bm) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, NUMBER_COMPRESS, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);
        }
    }
}
