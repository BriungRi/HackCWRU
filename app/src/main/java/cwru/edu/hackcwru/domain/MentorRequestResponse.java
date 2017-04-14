package cwru.edu.hackcwru.domain;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MentorRequestResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("locationDescription")
    @Expose
    private String locationDescription;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private MentorResponse response;
    @SerializedName("topics")
    @Expose
    private List<String> topics = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("mentee")
    @Expose
    private Mentee mentee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MentorResponse getResponse() {
        return response;
    }

    public void setResponse(MentorResponse response) {
        this.response = response;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
