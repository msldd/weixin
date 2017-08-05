package com.hust.weixin.model;

/**
 * Created by Administration on 2016/5/9.
 */
public class WeixinContext {
    public static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        WeixinContext.accessToken = accessToken;
    }
}
