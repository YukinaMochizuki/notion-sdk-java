package tw.yukina.notion.sdk.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Entity {

    @JsonIgnore
    String getEntitySnapshot();

    @JsonIgnore
    void setEntitySnapshot(String entitySnapshot);

    @JsonIgnore
    String getSessionUuid();

}
