package com.hust.weixin.kit.msg;

import com.hust.weixin.entity.User;
import com.hust.weixin.entity.WeixinMenu;
import com.hust.weixin.entity.WeixinQr;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.WUser;
import com.hust.weixin.model.WeixinFinalMessageValue;
import com.hust.weixin.service.IUserService;
import com.hust.weixin.service.IWeixinMenuService;
import com.hust.weixin.service.IWeixinQrService;
import com.hust.weixin.service.IWeixinTagService;
import com.hust.weixin.util.SpringBeanFactory;
import com.hust.weixin.web.filter.WeixinAuthFilter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/22.
 */
public class WeixinEventKit {
    private static final Logger logger = Logger.getLogger(WeixinMessageKit.class);

    public static String handlerEventMsg(Map<String, String> msgMap) throws IOException {
        String event = msgMap.get("Event");
        if (WeixinFinalMessageValue.MSG_EVENT_CLICK.equals(event)) {
            return handlerClickEvent(msgMap);
        } else if (WeixinFinalMessageValue.MSG_EVENT_SCAN.equals(event)) {
            return handlerScanEvent(msgMap);
        } else if (WeixinFinalMessageValue.MSG_EVENT_SUBSCRIBE.equals(event)) {
            return handlerSubscribeEvent(msgMap);
        } else if (WeixinFinalMessageValue.MSG_EVENT_UNSUBSCRIBE.equals(event)) {
            return handlerUnsubscribeEvent(msgMap);
        }
        return null;
    }


    /**
     * 用户关注时扫描二维码的处理事件操作
     *
     * @param msgMap
     */
    private static String handlerScanEvent(Map<String, String> msgMap) throws IOException {
        String openid = msgMap.get("FromUserName");
        User user = handlerUserInfo(openid);
        //用户关注时扫描二维码，得到场景值
        String snum = getScence(msgMap, false);
        if (snum != null) {
            IWeixinQrService weixinQrService = (IWeixinQrService) SpringBeanFactory.getBean("weixinQrService");
            WeixinQr qr = weixinQrService.loadBySnum(Integer.parseInt(snum));
            if (qr == null) {
                throw new SysException("该场景值在数据库中不存在对应的二维码");
            } else {
                if (qr.getType() == WeixinQr.REPASSWORD_TYPE) {
                    //处理修改密码的操作
                    return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "<a href=\"" + qr.getQrData() + "\">" + qr.getMsg() + "</a>"));
                } else if (qr.getType() == WeixinQr.SET_GROUP_TYPE) {
                    //处理设置用户组的操作
                    IWeixinTagService weixinTagService = (IWeixinTagService) SpringBeanFactory.getBean("weixinTagService");
                    List<String> openidList = new ArrayList<>();
                    openidList.add(openid);
                    weixinTagService.batchtagging(Integer.parseInt(qr.getQrData()), openidList);
                    return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "用户组修改成功,用户组修改为:" + qr.getQrData() +
                            ",目前用户所在的分组有：" + weixinTagService.getUserTagIds(openid)));
                } else if (qr.getType() == WeixinQr.TEMP_LOGIN) {
                    //处理登陆用户的操作
                    long t = System.currentTimeMillis() - qr.getCreateDate().getTime();
                    if (t / 1000 > 60) {
                        return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "你扫描的二维码已过期，请在网页中重新扫码"));
                    } else {
                        qr.setStatus(1);
                        qr.setQrData(openid);
                        weixinQrService.update(qr);
                    }

                } else if (qr.getType() == WeixinQr.TEMP_BIND) {
                    //处理绑定用户的操作
                }
            }
        }
        return null;
    }

    private static String handlerUnsubscribeEvent(Map<String, String> msgMap) {
        String openid = msgMap.get("FromUserName");
        IUserService userService = (IUserService) SpringBeanFactory.getBean("userService");
        User user = userService.loadByOpenid(openid);
        if (user != null) {
            user.setStatus(0);
            userService.update(user);
        }
        return null;
    }

    private static String handlerSubscribeEvent(Map<String, String> msgMap) throws IOException {
        String openid = msgMap.get("FromUserName");
        User user = handlerUserInfo(openid);
        //用户未关注时扫描二维码，得到场景值
        String snum = getScence(msgMap, true);
        if (snum != null) {
            IWeixinQrService weixinQrService = (IWeixinQrService) SpringBeanFactory.getBean("weixinQrService");
            WeixinQr qr = weixinQrService.loadBySnum(Integer.parseInt(snum));
            if (qr == null) {
                throw new SysException("该场景值在数据库中不存在对应的二维码");
            } else {
                if (qr.getType() == WeixinQr.SET_GROUP_TYPE) {
                    IWeixinTagService weixinTagService = (IWeixinTagService) SpringBeanFactory.getBean("weixinTagService");
                    List<String> openidList = new ArrayList<>();
                    openidList.add(openid);
                    weixinTagService.batchtagging(Integer.parseInt(qr.getQrData()), openidList);
                }
            }
        }
        if (user.getBind() == 0) {
            String bingUrl = WeixinAuthFilter.server_url + "/weixin/user/bindExistUser";
            return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "请点击绑定获得更好的体验<a href=\"" + bingUrl + "\">点击绑定</a>"));
        } else {
            String bingUrl = "http://www.baidu.com";
            return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "<a href=\"" + bingUrl + "\">欢迎您再次使用我们的微信平台，点击打开我们的页面</a>"));
        }
    }

    private static User handlerUserInfo(String openid) {
        IUserService userService = (IUserService) SpringBeanFactory.getBean("userService");
        User user = userService.loadByOpenid(openid);
        if (user == null) {
            WUser wu = userService.queryByOpenid(openid);
            user = wu.getUser();
            userService.add(user);
        } else {
            if (user.getStatus() == 0) {
                user.setStatus(1);
                userService.update(user);
            }
        }
        return user;
    }

    private static String getScence(Map<String, String> msgMap, boolean subscribe) {
        String key = msgMap.get("EventKey");
        if (key == null || "".equals(key)) return null;
        if (subscribe)
            return key.split("_")[1];
        else return key;

    }

    private static String handlerClickEvent(Map<String, String> msgMap) throws IOException {
        String keyCode = msgMap.get("EventKey");
        IWeixinMenuService weixinMenuService = (IWeixinMenuService) SpringBeanFactory.getBean("weixinMenuService");
        WeixinMenu wm = weixinMenuService.loadByKey(keyCode);
        if (wm != null && wm.getRespType() == 1) {
            Map<String, Object> map = MessageCreateKit.createTextMsg(msgMap, wm.getContent());
            return WeixinMessageKit.map2xml(map);
        }
        return null;
    }
}
