package com.hust.weixin.model;

/**
 * 消息管理
 * Created by Administration on 2016/6/23.
 */
public class WeixinFinalMessageValue {
    /**
     * 接收普通消息:文本消息
     * 被动回复用户消息:回复文本消息
     */
    public final static String MSG_TEXT_TYPE = "text";
    /**
     * 接收普通消息:图片消息
     * 被动回复用户消息:回复图片消息
     */
    public final static String MSG_IMAGE_TYPE = "image";
    /**
     * 接收普通消息:语音消息
     * 被动回复用户消息: 回复语音消息
     */
    public final static String MSG_VOICE_TYPE = "voice";
    /**
     * 接收普通消息:视频消息
     * 被动回复用户消息: 回复视频消息
     */
    public final static String MSG_VIDEO_TYPE = "video";
    /**
     * 接收普通消息:小视频消息
     */
    public final static String MSG_SHORTVIDEO_TYPE = "shortvideo";
    /**
     * 接收普通消息:地理位置消息
     */
    public final static String MSG_LOCATION_TYPE = "location";
    /**
     * 接收普通消息:链接消息
     */
    public final static String MSG_LINK_TYPE = "link";

    /**
     * 被动回复用户消息
     * 被动回复用户消息: 回复音乐消息
     */
    public final static String MSG_MUSICTYPE = "music";
    /**
     * 被动回复用户消息
     * 被动回复用户消息: 回复图文消息
     */
    public final static String MSG_NEWS_TYPE = "news";


    /**
     * 接收事件推送
     */
    public final static String MSG_EVENT_TYPE = "event";
    /**
     * 接收事件推送:用户未关注时，进行关注后的事件推送
     */
    public final static String MSG_EVENT_SUBSCRIBE = "subscribe";
    /**
     * 接收事件推送:用户取消关注时，进行取消关注后的事件推送
     */
    public final static String MSG_EVENT_UNSUBSCRIBE = "unsubscribe";
    /**
     * 接收事件推送:用户已关注时的事件推送
     */
    public final static String MSG_EVENT_SCAN = "SCAN";
    /**
     * 接收事件推送:上报地理位置事件
     */
    public final static String MSG_EVENT_LOCATION = "LOCATION";
    /**
     * 接收自定义菜单事件推送:点击菜单拉取消息时的事件推送
     */
    public final static String MSG_EVENT_CLICK = "CLICK";
    /**
     * 接收自定义菜单事件推送:点击菜单跳转链接时的事件推送
     */
    public final static String MSG_EVENT_VIEW = "VIEW";
    /**
     * 接收自定义菜单事件推送:扫码推事件的事件推送
     */
    public final static String MSG_EVENT_SCANCODE_PUSH = "scancode_push";
    /**
     * 接收自定义菜单事件推送:扫码推事件且弹出“消息接收中”提示框的事件推送
     */
    public final static String MSG_EVENT_SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 接收自定义菜单事件推送:弹出系统拍照发图的事件推送
     */
    public final static String MSG_EVENT_PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 接收自定义菜单事件推送:弹出拍照或者相册发图的事件推送
     */
    public final static String MSG_EVENT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 接收自定义菜单事件推送:弹出地理位置选择器的事件推送
     */
    public final static String MSG_EVENT_PIC_WEIXIN = "pic_weixin";
    /**
     * 接收自定义菜单事件推送:弹出微信相册发图器的事件推送
     */
    public final static String MSG_EVENT_LOCATION_SELECT = "location_select";

}
