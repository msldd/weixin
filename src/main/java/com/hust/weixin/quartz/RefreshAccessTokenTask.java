package com.hust.weixin.quartz;

import com.hust.weixin.json.AccessToken;
import com.hust.weixin.json.ErrorEntity;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Administration on 2016/5/9.
 */
@Component
public class RefreshAccessTokenTask {

    private static final Logger logger = Logger.getLogger(RefreshAccessTokenTask.class);

    public void refreshToken() {
        String url = WeixinFinalBasicValue.ACCESS_TOKEN_URL;
        url = url.replaceAll("APPID", WeixinFinalBasicValue.APPID);
        url = url.replaceAll("APPSECRET", WeixinFinalBasicValue.APPSECRET);
        String json = HttpClientUtil.getMsgToService(url);
        try {
            AccessToken at = (AccessToken) JsonUtil.getInstance().json2obj(json, AccessToken.class);
            logger.info("获取AccessToken成功:" + at);
            WeixinContext.setAccessToken(at.getAccess_token());
        } catch (Exception e) {
            ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
            logger.error("获取token异常：" + err);
            refreshToken();
        }
    }
}
