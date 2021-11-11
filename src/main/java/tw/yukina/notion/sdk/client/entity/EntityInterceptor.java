package tw.yukina.notion.sdk.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntityInterceptor implements EntitySession{

    @JsonIgnore
    private String sessionUuid;

    @JsonIgnore
    private String entitySnapshot;

    public EntityInterceptor(String entitySnapshot, String sessionUuid) {
        this.entitySnapshot = entitySnapshot;
        this.sessionUuid = sessionUuid;
    }
}
