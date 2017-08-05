package com.hust.weixin.service;

import com.hust.weixin.json.MsgRecord;
import com.hust.weixin.json.WeixinKf;
import com.hust.weixin.json.WeixinKfOnline;

import java.io.File;
import java.util.List;

/**
 * Created by Administration on 2016/6/25.
 */
public interface IWeixinKfService {

    /**
     * 获取客服基本信息
     *
     * @return
     */
    List<WeixinKf> list();

    /**
     * 获取客服基本信息
     *
     * @return
     */
    List<WeixinKfOnline> listOnline();


    /**
     * 添加客服帐号
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
     * @param nickname   客服昵称，最长16个字
     */
    void add(WeixinKf kf);

    /**
     * 邀请绑定客服帐号
     * 说明：新添加的客服帐号是不能直接使用的，只有客服人员用微信号绑定了客服账号后，方可登录Web客服进行操作。
     * 此接口发起一个绑定邀请到客服人员微信号，客服人员需要在微信客户端上用该微信号确认后帐号才可用。尚未绑定微信号的帐号可以进行绑定邀请操作，
     * 邀请未失效时不能对该帐号进行再次绑定微信号邀请。
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param invite_wx  接收绑定邀请的客服微信号
     */
    void invite(WeixinKf kf);

    /**
     * 设置客服信息
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param nickname   客服昵称，最长16个字
     */
    void update(WeixinKf kf);

    /**
     * 上传客服头像
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     * @param media      form-data 中媒体文件标识，有filename、filelength、content-type 等信息，文件大小为5M 以内
     */
    void uploadHeadImg(String kf_account, File media);

    /**
     * 删除客服帐号
     *
     * @param kf_account 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    void del(String kf_account);

    /**
     * 获取聊天记录
     *
     * @param starttime 起始时间，unix时间戳
     * @param endtime   结束时间，unix时间戳，每次查询时段不能超过24小时
     * @param pageindex 查询第几页，从1开始
     * @param pagesize  每页大小，最多50条
     */
    List<MsgRecord> getMsgRecord(long starttime, long endtime, int pageindex, int pagesize);
}
