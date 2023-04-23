package tw.yukina.notion.sdk.client.api.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.support.NotionExceptionWrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ApiClientHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);

    private final NotionExceptionWrapper notionExceptionWrapper;

    private final Object target;

    public ApiClientHandler(Object target, NotionExceptionWrapper notionExceptionWrapper){
        this.target = target;
        this.notionExceptionWrapper = notionExceptionWrapper;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            result = method.invoke(target, args);
        } catch (Exception e) {
            Throwable exception = getRootCause(e);

            if(exception instanceof NotionAPIException){
                throw notionExceptionWrapper.wrapException((NotionAPIException) exception);
            } else throw exception;
        }

        return result;
    }

    private Throwable getRootCause(Throwable exception){
        Throwable cause;
        while(null != (cause = exception.getCause()) && (exception != cause) ) exception = cause;
        return exception;
    }
}
