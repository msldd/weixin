package com.hust.weixin.service;

import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalQrValue;
import com.hust.weixin.util.HttpClientUtil;
import org.junit.Test;

/**
 * Created by Administration on 2016/6/25.
 */
public class WeixinQrServiceTest {
    private static final String ACCESS_TOKEN = "hfohNjA9AQcEosOR7ehc-8TF1g35Z1QtITPH1uKvZ-tjbhqb8XMxkN9YcgN8ly_3XIoww3yZ7F_X-TZNGkx8yufB7N4IVZyiksBXJVu3X8QsEefqezbnDE7h-aKH4AHXUQMaAGAQMI";

    @Test
    public void loadTicketByBaseQr() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        String url = WeixinFinalQrValue.QR_TICKET;
        url = url.replace("TOKEN", WeixinContext.getAccessToken());
        String json = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + 123 + "}}}";
        System.out.println(json);
        String rjson = HttpClientUtil.postMsgToService(url, json);
        System.out.println(rjson);
    }
}
