package com.hust.weixin.service;

import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.ErrorEntity;
import com.hust.weixin.json.MsgRecord;
import com.hust.weixin.json.WeixinKf;
import com.hust.weixin.json.WeixinKfOnline;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinFinalKfValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/25.
 */
@Service
public class WeixinKfService implements IWeixinKfService {

    private static Logger logger = Logger.getLogger(WeixinKfService.class);

    @Override
    public List<WeixinKf> list() {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.GET_KF_LIST);
        String json = HttpClientUtil.getMsgToService(url);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<WeixinKf>> kfMap = (Map<String, List<WeixinKf>>) JsonUtil.getInstance().json2obj(json, Map.class);
            logger.info("获取客服基本信息列表成功:" + kfMap.get("kf_list"));
            return kfMap.get("kf_list");
        }
        logger.info("获取客服基本信息列表失败");
        return null;
    }

    @Override
    public List<WeixinKfOnline> listOnline() {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.GET_ONLINE_KF_LIST);
        String json = HttpClientUtil.getMsgToService(url);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<WeixinKfOnline>> kfOnlineMap = (Map<String, List<WeixinKfOnline>>) JsonUtil.getInstance().json2obj(json, Map.class);
            logger.info("获取客服基本信息在线列表成功:" + kfOnlineMap.get("kf_online_list"));
            return kfOnlineMap.get("kf_online_list");
        }
        logger.info("获取客服基本信息在线列表失败");
        return null;
    }

    @Override
    public void add(WeixinKf kf) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_ADD);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("nickname", kf.getKf_nick());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("添加客服帐号异常：" + err);
        } else {
            logger.info("添加客服帐号成功:" + err);
        }
    }

    @Override
    public void invite(WeixinKf kf) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_INVITE);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("invite_wx", kf.getKf_wx());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("邀请绑定客服帐号异常：" + err);
        } else {
            logger.info("邀请绑定客服帐号成功:" + err);
        }
    }

    @Override
    public void update(WeixinKf kf) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_UPDATE);
        Map<String, String> map = new HashMap<>();
        map.put("kf_account", kf.getKf_account());
        map.put("nickname", kf.getKf_nick());
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("设置客服信息异常：" + err);
        } else {
            logger.info("设置客服信息成功:" + err);
        }
    }

    @Override
    public void uploadHeadImg(String kf_account, File media) {

    }

    @Override
    public void del(String kf_account) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_ACCOUNT_DEL);
        url = url.replace("KFACCOUNT", kf_account);
        String json = HttpClientUtil.getMsgToService(url);
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("设置客服信息异常：" + err);
        } else {
            logger.info("设置客服信息成功:" + err);
        }
    }

    @Override
    public List<MsgRecord> getMsgRecord(long starttime, long endtime, int pageindex, int pagesize) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalKfValue.KF_MSG_RECORD);
        String json = HttpClientUtil.getMsgToService(url);
        if (WeixinBasicKit.checkRequestSucc(json)) {
            Map<String, List<MsgRecord>> recordMap = (Map<String, List<MsgRecord>>) JsonUtil.getInstance().json2obj(json, Map.class);
            logger.info("获取客服聊天记录成功:" + recordMap.get("recordlist"));
            return recordMap.get("recordlist");
        }
        logger.info("获取客服聊天记录失败");
        return null;
    }
}
