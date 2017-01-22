package cwru.edu.hackcwru.data;

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
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return simplifyTime(startTime);
    }

    public String getEndTime() {
        return simplifyTime(endTime);
    }

    private String simplifyTime(String timeToSimplify) {
        if(timeToSimplify != null){
            final int dateIndex = 0;
            final int timeIndex = 1;

            String[] timeParts = timeToSimplify.split(" ");
            String date = timeParts[dateIndex];
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}