package com.hust.weixin.service;

/**
 * Created by Administration on 2016/6/25.
 */
public interface IWeixinKfSessionService {
    /**
     * 创建会话
     * 说明：此接口在客服和用户之间创建一个会话，如果该客服和用户会话已存在，则直接返回0。指定的客服帐号必须已经绑定微信号且在线。
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param openid     粉丝的openid
     */
    void add(String kf_account, String openid);

    /**
     * 关闭会话
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param openid     粉丝的openid
     */
    void close(String kf_account, String openid);

    /**
     * 获取客户会话状态
     *
     * @param openid 粉丝的openid
     */
    void get(String openid);

    /**
     * 获取客服会话列表
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    void list(String kf_account);

    /**
     * 获取未接入会话列表
     */
    void getWaitCase();
}
