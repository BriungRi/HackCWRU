package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class MapList {
    @SerializedName("maps")
    @Expose
    private List<Map> maps = null;

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
