import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class JsonTest {
    @Test
    void testTime(){
        //2023-05-12 20:10:46.838102 +0800
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS Z");
        log.info(timeFormatter.format(ZonedDateTime.now()));

        //2023-05-12 20:15:27.624983 Asia/Shanghai
        log.info(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS VV").format(ZonedDateTime.now()));

    }


    @Test
    void testJson() throws JsonProcessingException {
        String originalData = "{\"id\":\"chatcmpl-7HamhmBwah5YonzaxaMFMUrs1nOOW\",\"object\":\"chat.completion.chunk\",\"created\":1684427331,\"model\":\"gpt-35-turbo\",\"choices\":[{\"index\":0,\"finish_reason\":null,\"delta\":{\"content\":\"?×÷\"},\"content_filter_result\":{\"error\":{\"code\":\"content_filter_error\",\"message\":\"The contents are not filtered\"}}}],\"usage\":null}";

        originalData = "{\"id\":\"\",\"object\":\"\",\"created\":0,\"model\":\"\",\"prompt_filter_results\":[{\"prompt_index\":0,\"content_filter_results\":{\"hate\":{\"filtered\":false,\"severity\":\"safe\"},\"self_harm\":{\"filtered\":false,\"severity\":\"safe\"},\"sexual\":{\"filtered\":false,\"severity\":\"safe\"},\"violence\":{\"filtered\":false,\"severity\":\"safe\"}}}],\"choices\":[]}";
        originalData = "{\"id\":\"chatcmpl-8x47M6MeJg8wrAhe7obtlHdPcJx4i\",\"object\":\"chat.completion.chunk\",\"created\":1709087872,\"model\":\"gpt-35-turbo-16k\",\"choices\":[{\"finish_reason\":null,\"index\":0,\"delta\":{\"role\":\"assistant\",\"content\":\"\"},\"content_filter_results\":{},\"logprobs\":null}]}";
//        originalData = "{\"id\":\"chatcmpl-8x47M6MeJg8wrAhe7obtlHdPcJx4i\",\"object\":\"chat.completion.chunk\",\"created\":1709087872,\"model\":\"gpt-35-turbo-16k\",\"choices\":[{\"finish_reason\":null,\"index\":0,\"delta\":{\"content\":\"¼Ó\"},\"content_filter_results\":{\"hate\":{\"filtered\":false,\"severity\":\"safe\"},\"self_harm\":{\"filtered\":false,\"severity\":\"safe\"},\"sexual\":{\"filtered\":false,\"severity\":\"safe\"},\"violence\":{\"filtered\":false,\"severity\":\"safe\"}},\"logprobs\":null}]}";

        final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        var result = OBJECT_MAPPER.readValue(originalData, ChatCompletionResponse.class);

        log.info("{}", result);
    }
}
