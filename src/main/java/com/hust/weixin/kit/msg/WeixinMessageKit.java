package com.hust.weixin.kit.msg;

import com.hust.weixin.json.TemplateMsg;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalMessageValue;
import com.hust.weixin.model.WeixinFinalTemplateValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.*;

/**
 * 用户消息
 * Created by Administration on 2016/6/20.
 */
public class WeixinMessageKit {
    private static final Logger logger = Logger.getLogger(WeixinMessageKit.class);

    private static Map<String, String> replyMsgs = new HashMap<>();

    static {
        replyMsgs.put("123", "你输入了123");
        replyMsgs.put("hello", "word");
        replyMsgs.put("run", "祝你一路平安!");
        replyMsgs.put("欢欢", "肖旭文，爱你！");
    }

    public static String handlerReceiveMsg(HttpServletRequest request) throws IOException {
        Map<String, String> msgMap = reqMsg2Map(request);
        if (DuplicateMessageKit.checkDuplicate(msgMap)) {
            String rel = DuplicateMessageKit.getRel(msgMap);
            return rel;
        }
        String msgType = msgMap.get("MsgType");
        if (WeixinFinalMessageValue.MSG_EVENT_TYPE.equals(msgType.trim())) {
            String rel = WeixinEventKit.handlerEventMsg(msgMap);
            DuplicateMessageKit.setRel(msgMap, rel);
            return rel;
        } else {
            return handlerCommonMsg(msgMap);
        }
    }

    public static Map<String, String> reqMsg2Map(HttpServletRequest request) throws IOException {
        String xml = req2xml(request);
        Map<String, String> maps = new HashMap<>();
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> eles = root.elements();
            for (Element e : eles) {
                maps.put(e.getName(), e.getTextTrim());
            }
            logger.info("接收用户消息[map类型]:" + maps);
            return maps;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String req2xml(HttpServletRequest request) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String str;
        StringBuffer sb = new StringBuffer();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        logger.info("接收用户消息[xml类型]:" + sb.toString());
        return sb.toString();
    }

    public static String map2xml(Map<String, Object> map) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement("xml");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Object o = map.get(key);
            if (o instanceof String) {
                String t = o.toString();
                if (t.indexOf("<a") >= 0) {
                    root.addElement(key).addCDATA(t);
                } else {
                    root.addElement(key).addText(o.toString());
                }
            } else {

            }
        }
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw);
        xw.setEscapeText(false);
        xw.write(d);
        logger.info("[map-->xml类型]:" + sw.toString());
        return sw.toString();
    }

    private static String handlerCommonMsg(Map<String, String> msgMap) {
        return null;
    }

    public static String handlerMsg(Map<String, String> msgMap) throws IOException {
        String msgType = msgMap.get("MsgType");
        if (msgType.equals(WeixinFinalMessageValue.MSG_EVENT_TYPE)) {

        } else if (msgType.equals(WeixinFinalMessageValue.MSG_TEXT_TYPE)) {
            return text_ImageTypeHandler(msgMap);
        } else if (msgType.equals(WeixinFinalMessageValue.MSG_IMAGE_TYPE)) {
            return imageTypeHandler(msgMap, "ljdI1uniNXvHMmehDMYAAhC4Ou034wyGQnaODnsVohNw9mZRBDT41fU5JvwqdKmy");
        }
        return null;
    }

    private static String imageTypeHandler(Map<String, String> msgMap, String mediaId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("ToUserName", msgMap.get("FromUserName"));
        map.put("FromUserName", msgMap.get("ToUserName"));
        map.put("CreateTime", new Date().getTime() + "");
        map.put("MsgType", "image");
        map.put("Image", "<MediaId>" + mediaId + "</MediaId>");

        return map2xml(map);
    }

    private static String textTypeHandler(Map<String, String> msgMap) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("ToUserName", msgMap.get("FromUserName"));
        map.put("FromUserName", msgMap.get("ToUserName"));
        map.put("CreateTime", new Date().getTime() + "");
        map.put("MsgType", "text");
        String replyContent = "你请求的消息内容不正确!";
        String con = msgMap.get("Content");
        if (replyMsgs.containsKey(con)) {
            replyContent = replyMsgs.get(con);
        }
        map.put("Content", replyContent);
        return map2xml(map);
    }

    private static String text_ImageTypeHandler(Map<String, String> msgMap) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String con = msgMap.get("Content");
        map.put("ToUserName", msgMap.get("FromUserName"));
        map.put("FromUserName", msgMap.get("ToUserName"));
        map.put("CreateTime", new Date().getTime() + "");
        if (con.equals("我")) {
            map.put("MsgType", "image");
            map.put("Image", "<MediaId>" + "ljdI1uniNXvHMmehDMYAAhC4Ou034wyGQnaODnsVohNw9mZRBDT41fU5JvwqdKmy" + "</MediaId>");
        } else if (con.equals("爱")) {
            map.put("MsgType", "image");
            map.put("Image", "<MediaId>" + "ShNpQYffcUUNlDMHqhzQbam2TmzUjziBT_IYI5WInywEthai8YXLEu4CBXUjsWku" + "</MediaId>");
        } else if (con.equals("你")) {
            map.put("MsgType", "image");
            map.put("Image", "<MediaId>" + "eUF1XBY3i8ZC_0tEXPRIP9MW5zYvP7yiIYp1YP4ZGAtLjkMSB4sfP8Ypki_k8vFq" + "</MediaId>");
        } else {
            map.put("MsgType", "text");
            String replyContent = "你请求的消息内容不正确!";
            if (replyMsgs.containsKey(con)) {
                replyContent = replyMsgs.get(con);
            }
            map.put("Content", replyContent);
        }
        return map2xml(map);
    }


    public static String postTemplateMsg(TemplateMsg tm) {
        String json = JsonUtil.getInstance().obj2json(tm);
        String url = WeixinFinalTemplateValue.POST_TEMPLATE_MSG;
        url = url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
        HttpClientUtil.postMsgToService(url, json);
        return null;
    }
}
