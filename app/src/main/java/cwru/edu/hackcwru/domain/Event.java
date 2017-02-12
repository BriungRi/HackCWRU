package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Event {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("startTime")
    @Expose
    private String startDateTime;
    @SerializedName("endTime")
    @Expose
    private String endDateTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    private boolean isSaved;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    // TODO: Prettify this
    public String getDateTimeToDisplay() {
        String dateToDisplay = this.getDate(getStartDateTime()); //Friday/Saturday/Sunday
        String timeToDisplay = this.getTime(getStartDateTime()) + " - " + this.getTime(getEndDateTime());
        return dateToDisplay + " " + timeToDisplay;
    }

    private String getDate(String dateTime) {
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
            if(day.equals(FRIDAY))
                day = "Friday";
            else if(day.equals(SATURDAY))
                day = "Saturday";
            else if(day.equals(SUNDAY))
                day = "Sunday";

            return day;
        }
        return null;
    }

    // TODO: Convert time to 1-12 AM/PM
    private String getTime(String dateTime) {
        if (dateTime != null) {
            final int timeIndex = 1;

            String[] timeParts = dateTime.split(" ");
            String time = timeParts[timeIndex];
            return time.substring(0, time.length() - 3);
        }
        return null;
    }

    public String getLocation() {
        return location;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean state) {
        isSaved = state;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}