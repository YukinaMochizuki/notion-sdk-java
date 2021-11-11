package tw.yukina.notion.sdk.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
public class EntityInterceptor implements Entity {

    @JsonIgnore
    private final String sessionUuid;

    @Setter
    @JsonIgnore
    private String entitySnapshot;

    public EntityInterceptor(String entitySnapshot, String sessionUuid) {
        this.entitySnapshot = entitySnapshot;
        this.sessionUuid = sessionUuid;
    }
}
