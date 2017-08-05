package com.hust.weixin;

import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.model.WeixinFinalUserValue;
import com.hust.weixin.util.HttpClientUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administration on 2016/6/23.
 */
public class WeixinAuthTest {
    @Test
    public void testAuth() throws UnsupportedEncodingException {
        String url = WeixinFinalUserValue.OAUTH_URL;
        url = url.replace("APPID", WeixinFinalBasicValue.APPID)
                .replace("REDIRECT_URI", URLEncoder.encode("http://www.baidu.com", "UTF-8"))
                .replace("SCOPE", "snsapi_userinfo")
                .replace("STATE", "380481791");
        System.out.println(HttpClientUtil.getMsgToService(url));
    }
}
