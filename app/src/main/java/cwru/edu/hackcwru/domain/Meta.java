package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Meta {

    @SerializedName("opened")
    @Expose
    private String opened;

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}