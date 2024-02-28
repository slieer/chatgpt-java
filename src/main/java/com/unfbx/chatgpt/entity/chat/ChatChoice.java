package com.unfbx.chatgpt.entity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 * @since 2023-03-02
 */
@Data
public class ChatChoice implements Serializable {
    private long index;
    /**
     * 请求参数stream为true返回是delta
     */
    @JsonProperty("delta")
    private Message delta;
    /**
     * 请求参数stream为false返回是message
     */
    @JsonProperty("message")
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;

    //azure openai
    @JsonProperty("content_filter_results")
    private ContentFilterResultV1 contentFilterResult;

    @Data
    public static class ContentFilterResult {
        Error error;

        @Data
        public static class Error{
            String code;
            String message;
        }
    }

    @Data
    public static class ContentFilterResultV1 {
        Error_ hate;
        @JsonProperty("self_harm")
        Error_ selfHarm;
        Error_ sexual;
        Error_ violence;

        @Data
        public static class Error_{
            boolean filtered;
            String severity;
        }
    }


}
