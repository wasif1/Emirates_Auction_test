package com.ea.Components;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String dateFormate = "d MM  HH:mm a";


    public static Date returnDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public static String daysLeft(String expireDateTime) {
        long diff = Utils.returnDate(expireDateTime).getTime() - Utils.getCurrentDateTime().getTime();
        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return String.valueOf(days);
    }

    public static Date getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
        Date c = new Date();
        System.out.println("Current time => " + c);
        return c;
    }


    public static String getLocalizedFormattedDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date dateObj1 = null;
        try {
            dateObj1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        cal.setTime(dateObj1);
        int day = cal.get(Calendar.DATE);

        return new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateObj1);
    }

    public static long getTime(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date dateObj1 = null;
        try {
            dateObj1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateObj1 != null) {
            return dateObj1.getTime();
        } else {
            return 0;
        }
    }

    public static Date getDate(String date) {
        if (date.isEmpty()) {
            return null;
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date dateObj1 = null;
        try {
            dateObj1 = inputFormat.parse(date);
            inputFormat.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObj1;
    }

    public static String convertDateToString(Date dt) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        return simpleDate.format(dt);
    }

    public static String convertDAteTime(String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        if (!TextUtils.isEmpty(date)) {
            Date d = null;
            try {
                d = input.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formatted = output.format(d);
            Log.i("DATE", "" + formatted);
            return formatted;
        } else {
            return "";
        }
    }


    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    private static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getTimeAgo(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        if (time < 1000000000000L) {
            time *= 1000;
        }

        long now = currentDate().getTime();

        final long diff = now - time;
        if (0 < diff && diff < MINUTE_MILLIS) {
            return "moments ago";
        } else if (0 < diff && diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (0 < diff && diff < 60 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (0 < diff && diff < 2 * HOUR_MILLIS) {
            return "an hour ago";
        } else if (0 < diff && diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (0 < diff && diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else if (0 < diff && diff < 7 * 24 * HOUR_MILLIS) {
            return diff / DAY_MILLIS + " days ago";
        } else {
            return getLocalizedFormattedDate(convertDateToString(date));
        }
    }
}