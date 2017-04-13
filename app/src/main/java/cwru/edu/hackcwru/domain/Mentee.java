package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Mentee {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    public Mentee(){
        // Empty Constructor
    }

    public Mentee(String name, String deviceId){
        this.name = name;
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}