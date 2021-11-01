package tw.yukina.notion.sdk.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EntitySession {

    @JsonIgnore
    String getEntitySnapshot();

    @JsonIgnore
    String getSessionUuid();

}
