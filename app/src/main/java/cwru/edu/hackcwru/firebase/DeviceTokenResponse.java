package cwru.edu.hackcwru.firebase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeviceTokenResponse {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("deviceToken")
    @Expose
    public String deviceToken;
    @SerializedName("os")
    @Expose
    public String os;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}