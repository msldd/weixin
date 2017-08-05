package com.hust.weixin;

import com.fasterxml.jackson.databind.JsonNode;
import com.hust.weixin.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administration on 2016/6/24.
 */
public class JsonTest {
    @Test
    public void testJson() throws IOException {
        String json = "{\"tag\":{\"id\":111,\"name\":\"测试组19\"}}";
        JsonNode jn = JsonUtil.getMapper().readTree(json);
        System.out.println("JsnNode:"+jn);
        System.out.println(jn.get("tag").get("id").asInt());
    }
}
