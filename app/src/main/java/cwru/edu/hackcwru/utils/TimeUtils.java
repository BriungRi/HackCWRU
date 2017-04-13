package cwru.edu.hackcwru.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {

    public static String prettifyTime(String dateTime) {
        String dateToDisplay = getDayOfWeek(dateTime);
        String timeToDisplay = getTime(dateTime);
        return dateToDisplay + " " + timeToDisplay;
    }

    private static String getTime(String dateTime) {
        if (dateTime != null) {
            final int TIME_INDEX = 1;

            String time = dateTime.split(" ")[TIME_INDEX];
            String regularTime = convertFromMilitaryTime(time);

            return regularTime;
        }
        return null;
    }

    private static String convertFromMilitaryTime(String time) {
        final int HOUR_INDEX = 0;
        final int MINUTE_INDEX = 1;

        String[] hoursMinutesSeconds = time.split(":");
        int hour = Integer.parseInt(hoursMinutesSeconds[HOUR_INDEX]);

        if (hour > 12) {
            hour = hour % 12;
            return hour + ":" + hoursMinutesSeconds[MINUTE_INDEX] + "PM";
        } else {
            return hour + ":" + hoursMinutesSeconds[MINUTE_INDEX] + "AM";
        }
    }

    public static long getEpochFromDateTime(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static String getDayOfWeek(String time) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
            return getStringDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getStringDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
        }
        return "";
    }
}
