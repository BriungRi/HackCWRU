package cwru.edu.hackcwru.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {

    // TODO: Prettify this
    public static String prettifyTime(String dateTime) {
        String dateToDisplay = getDate(dateTime); //Friday/Saturday/Sunday
        String timeToDisplay = getTime(dateTime);
        return dateToDisplay + " " + timeToDisplay;
    }

    private static String getDate(String dateTime) {
        if (dateTime != null) {
            final int DATE_INDEX = 0;
            final int DAY_INDEX = 2;
            // TODO: Figure out a way to get rid of hard code
            final String FRIDAY = "27";
            final String SATURDAY = "28";
            final String SUNDAY = "29";

            String[] timeParts = dateTime.split(" ");
            String date = timeParts[DATE_INDEX];

            String day = date.split("-")[DAY_INDEX];
            if (day.equals(FRIDAY))
                day = "Friday";
            else if (day.equals(SATURDAY))
                day = "Saturday";
            else if (day.equals(SUNDAY))
                day = "Sunday";

            return day;
        }
        return null;
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
    private static long getEpochFromDateTime(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
