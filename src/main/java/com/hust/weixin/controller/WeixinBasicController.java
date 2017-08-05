package com.hust.weixin.controller;

import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.kit.msg.WeixinMessageKit;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalBasicValue;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Administration on 2016/6/21.
 */
@Controller
public class WeixinBasicController {
    private static final Logger logger = Logger.getLogger(WeixinController.class);

    @RequestMapping(value = "/wget", method = RequestMethod.GET)
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //signature	    微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        //timestamp	    时间戳
        //nonce	        随机数
        //echostr	    随机字符串
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.info("接入参数:" + "[signature:" + signature + "," + "timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr + "]");
        String arrs[] = {WeixinFinalBasicValue.TOKEN, nonce, timestamp};
        Arrays.sort(arrs);
        StringBuffer sb = new StringBuffer();
        for (String str : arrs) {
            sb.append(str);
        }
        String sha1 = WeixinBasicKit.sha1(sb.toString());

        if (sha1.equals(signature)) {
            response.getWriter().println(echostr);
            logger.info("微信公众开发平台接入成功");
        }
    }

    @RequestMapping(value = "/wget", method = RequestMethod.POST)
    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String respCon = WeixinMessageKit.handlerReceiveMsg(request);
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (!WeixinBasicKit.isEmpty(respCon)) {
            logger.info("返回用户消息[xml类型]:" + respCon);
            response.getWriter().write(respCon);
        }
    }

    @RequestMapping("/at")
    public void testAccessToken(HttpServletResponse response) throws IOException {
        response.getWriter().println(WeixinContext.getAccessToken());
    }
}
