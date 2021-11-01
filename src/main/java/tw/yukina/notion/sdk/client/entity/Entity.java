package tw.yukina.notion.sdk.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Getter
@Setter
public class Entity implements MethodInterceptor {

    @JsonIgnore
    private String sessionUuid;

    @JsonIgnore
    private String entitySnapshot = "";

    private final Object target;

    public Entity(Object target, String entitySnapshot, String sessionUuid) {
        this.target = target;
        this.entitySnapshot = entitySnapshot;
        this.sessionUuid = sessionUuid;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(method.getName().equals("getEntitySnapshot")){
            return entitySnapshot;
        } else if(method.getName().equals("getSessionUuid")){
            return sessionUuid;
        }
        return method.invoke(target, args);
    }
}
