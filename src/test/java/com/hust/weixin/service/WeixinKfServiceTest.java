package com.hust.weixin.service;

import com.hust.weixin.json.ErrorEntity;
import com.hust.weixin.json.MsgRecord;
import com.hust.weixin.json.WeixinKf;
import com.hust.weixin.json.WeixinKfOnline;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.model.WeixinFinalKfValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/25.
 */
public class WeixinKfServiceTest {
    private static final String ACCESS_TOKEN = "JmCMzW0nhQU4fhocZfIvqYixd_xqn4S2x0JuyBEDqMKp1p6luX0x571GaOrtAv_KZabeFn9fteyH5a7knuhJKaygE6vzKwdqj_91k2BI_7Wb9gisd5MCD3VcrE1dELamZQHjADAVMD";

    private WeixinKf kf;

    @Before
    public void setUp() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        kf = new WeixinKf();
        kf.setKf_account("xiaoxiao@" + WeixinFinalBasicValue.ACCOUNT);
        kf.setKf_nick("客服1");
    }

    @Test
    public void list() throws Exception {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.GET_KF_LIST);
        String json = HttpClientUtil.getMsgToService(url);
        System.out.println(json);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<WeixinKf>> kfMap = (Map<String, List<WeixinKf>>) JsonUtil.getInstance().json2obj(json, Map.class);
            System.out.println(kfMap);
        }
    }

    @Test
    public void listOnline() throws Exception {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.GET_ONLINE_KF_LIST);
        String json = HttpClientUtil.getMsgToService(url);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<WeixinKfOnline>> kfOnlineMap = (Map<String, List<WeixinKfOnline>>) JsonUtil.getInstance().json2obj(json, Map.class);
            System.out.println(kfOnlineMap);
        }
    }

    @Test
    public void add() throws Exception {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_ADD);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("nickname", kf.getKf_nick());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        System.out.println(err);
    }

    @Test
    public void invite() throws Exception {
        WeixinKf kf = new WeixinKf();
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_INVITE);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("invite_wx", kf.getKf_wx());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        System.out.println(err);
    }

    @Test
    public void update() throws Exception {
        WeixinKf kf = new WeixinKf();
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_UPDATE);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("nickname", kf.getKf_nick());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
    }

    @Test
    public void uploadHeadImg() throws Exception {


    }

    @Test
    public void del() throws Exception {
        WeixinKf kf = new WeixinKf();
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_DEL);
        url = url.replace("KFACCOUNT", kf.getKf_account());
        String json = HttpClientUtil.getMsgToService(url);
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
    }

    @Test
    public void getMsgRecord() throws Exception {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_MSG_RECORD);
        String json = HttpClientUtil.getMsgToService(url);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<MsgRecord>> recordMap = (Map<String, List<MsgRecord>>) JsonUtil.getInstance().json2obj(json, Map.class);
            System.out.println(recordMap);
        }
    }

}