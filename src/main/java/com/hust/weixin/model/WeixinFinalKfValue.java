package com.hust.weixin.model;

/**
 * 客服
 * Created by Administration on 2016/6/25.
 */
public class WeixinFinalKfValue {
    /**
     * 获取客服基本信息 URL:GET
     */
    public final static String GET_KF_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
    /**
     * 获取客服基本信息 URL:GET
     */
    public final static String GET_ONLINE_KF_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN";
    /**
     * 添加客服帐号 URL:POST示例如下： { "kf_account" : "test1@test", "nickname" : "客服1" }
     */
    public final static String KF_ACCOUNT_ADD = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
    /**
     * 邀请绑定客服帐号 URL:POST示例如下： { "kf_account" : "test1@test", "invite_wx" : "test_kfwx" }
     */
    public final static String KF_ACCOUNT_INVITE = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=ACCESS_TOKEN";
    /**
     * 设置客服信息 URL:POST 示例如下： { "kf_account" : "test1@test", "nickname" : "客服1" }
     */
    public final static String KF_ACCOUNT_UPDATE = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
    /**
     * 上传客服头像 URL:POST/FORM
     */
    public final static String KF_ACCOUNT_UPLOADHEADIMG = "https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
    /**
     * 删除客服帐号 URL:GET
     */
    public final static String KF_ACCOUNT_DEL = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";


    /**
     * 创建会话 URL:POST示例如下： { "kf_account" : "test1@test", "openid" : "OPENID" }
     */
    public final static String KF_SESSION_CREATE = "https://api.weixin.qq.com/customservice/kfsession/create?access_token=ACCESS_TOKEN";
    /**
     * 关闭会话 URL:POST示例如下： { "kf_account" : "test1@test", "openid" : "OPENID" }
     */
    public final static String KF_SESSION_CLOSE = "https://api.weixin.qq.com/customservice/kfsession/close?access_token=ACCESS_TOKEN";
    /**
     * 获取客户会话状态 URL:GET
     */
    public final static String KF_SESSION_GET = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=ACCESS_TOKEN&openid=OPENID";
    /**
     * 获取客服会话列表 URL:GET
     */
    public final static String KF_SESSION_LIST = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
    /**
     * 获取未接入会话列表 URL:GET
     */
    public final static String KF_SESSION_GETWAITCASE = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=ACCESS_TOKEN";


    /**
     * 获取聊天记录 URL:POST示例如下： { "starttime" : 987654321, "endtime" : 987654321, "pageindex" : 1, "pagesize" : 10 }\
     */
    public final static String KF_MSG_RECORD = "https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=ACCESS_TOKEN";
}
