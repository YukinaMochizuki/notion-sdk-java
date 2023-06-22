package tw.yukina.notion.sdk.client.api;

import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger("HttpClientLogger");

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Sending request " + chain.request().method() + ": " + chain.request().url());
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        LOGGER.info("Received response for " + chain.request().url() + " in " + (endTime - startTime) + "ms");

        return response;
    }
}
