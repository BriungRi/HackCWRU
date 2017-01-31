package cwru.edu.hackcwru.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnnouncementsList {

    @SerializedName("announcements")
    @Expose
    private List<Announcement> announcements = null;

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}