package com.hust.weixin.model;

/**
 * 二维码
 * <p>
 * <p>
 * Created by Administration on 2016/6/24.
 */
public class WeixinFinalQrValue {
    /**
     * 临时
     */
    public static final String QR_SCENE = "QR_SCENE";
    /**
     * 永久
     */
    public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    /**
     * 永久的字符串参数值
     */
    public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    /**
     * 创建二维码ticket URL:POST
     * 临时二维码POST数据例子：{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 永久二维码POST数据例子:{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 或创建字符串形式的二维码参数：{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}
     */
    public static final String QR_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    /**
     * 通过ticket换取二维码 URL:GET
     * 说明：本接口无须登录态即可调用,TICKET记得进行UrlEncode
     */
    public static final String QR_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
}
