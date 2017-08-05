package com.hust.weixin.kit.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建被动回复用户消息
 * Created by Administration on 2016/6/22.
 */
public class MessageCreateKit {
    /**
     * 回复文本消息
     *
     * @param msgMap
     * @param content
     * @return
     */
    public static Map<String, Object> createTextMsg(Map<String, String> msgMap, String content) {
        Map<String, Object> tm = new HashMap<>();
        tm.put("ToUserName", msgMap.get("FromUserName"));       //ToUserName	是	接收方帐号（收到的OpenID）
        tm.put("FromUserName", msgMap.get("ToUserName"));       //FromUserName	是	开发者微信号
        tm.put("CreateTime", System.currentTimeMillis() + "");  //CreateTime	是	消息创建时间 （整型）
        tm.put("MsgType", "text");                              //MsgType	    是	text
        tm.put("Content", content);                             //ontent	    是	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
        return tm;
    }
}
