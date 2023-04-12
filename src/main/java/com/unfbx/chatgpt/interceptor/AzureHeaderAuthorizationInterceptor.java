package com.unfbx.chatgpt.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.unfbx.chatgpt.function.KeyStrategyFunction;
import lombok.Getter;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

/**
 * 描述：请求增加header apikey
 *
 * @author grt
 * @since 2023-03-23
 */
@Getter
public class AzureHeaderAuthorizationInterceptor implements Interceptor {
    /**
     * key 集合
     */
    private List<String> apiKey;
    /**
     * 自定义的key的使用策略
     */
    private KeyStrategyFunction<List<String>, String> keyStrategy;

    public AzureHeaderAuthorizationInterceptor(List<String> apiKey, KeyStrategyFunction<List<String>, String> keyStrategy) {
        this.apiKey = apiKey;
        this.keyStrategy = keyStrategy;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("api-key", keyStrategy.apply(apiKey))
                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
