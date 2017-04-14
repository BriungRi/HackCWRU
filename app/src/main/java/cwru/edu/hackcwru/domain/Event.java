package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cwru.edu.hackcwru.utils.TimeUtils;

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

    public String getPrettyStartDateTime() {
        return TimeUtils.prettifyTime(startDateTime);
    }

    public String getPrettyEndDateTime() {
        return TimeUtils.prettifyTime(endDateTime);
    }

    public String getStartDateTime(){
        return startDateTime;
    }

    public String getEndDateTime(){
        return endDateTime;
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