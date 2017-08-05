package com.hust.weixin.model;

/**
 * Created by Administration on 2016/5/9.
 */
public class WeixinFinalBasicValue {

    /**
     * 测试微信号
     */
    public static final String ACCOUNT = "gh_41c355184c4b";
    /**
     * Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
     */
    public static final String TOKEN = "xiaoxuwen";

    /**
     * 测试号信息:第三方用户唯一凭证
     */
    public final static String APPID = "wxbf18d8b2a1d3f765";
    /**
     * 测试号信息:第三方用户唯一凭证密钥
     */
    public final static String APPSECRET = "1a0938b71005117eaa136a21d36ecca6";
    /**
     * 获取access_token URL:GET
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。
     * access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     */
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
}
