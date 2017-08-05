package com.hust.weixin.service;

import com.hust.weixin.json.WeixinBaseMediaNews;
import com.hust.weixin.json.WeixinTempMedia;

import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/26.
 */
public interface IWeixinMediaService {

    /**
     * 新增临时素材
     * 请注意：
     * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
     * 2、media_id是可复用的。
     * 3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
     *
     * @param type 是 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param path 是	media路径
     * @return
     */
    WeixinTempMedia addTempMedia(String type, String path);

    /**
     * 公众号可以使用本接口获取临时素材（即下载临时的多媒体文件）。请注意，视频文件不支持https下载，调用该接口需http协议。
     *
     * @param media_id 是  媒体文件ID
     * @param dir      保存的文件目录
     */
    void queryTempMedia(String media_id, String dir);

    /**
     * 新增永久图文素材
     * title	是	标题
     * thumb_media_id	是	图文消息的封面图片素材id（必须是永久mediaID）
     * author	是	作者
     * digest	是	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     * show_cover_pic	是	是否显示封面，0为false，即不显示，1为true，即显示
     * content	是	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     * content_source_url	是	图文消息的原文地址，即点击“阅读原文”后的URL
     *
     * @param bm
     */
    String addBaseMediaNews(WeixinBaseMediaNews bm);

    /**
     * 上传图文消息内的图片获取URL
     * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     *
     * @param path
     */
    String uploadImg(String path);

    /**
     * 新增其他类型永久素材
     * type	是	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * media	是	form-data中媒体文件标识，有filename、filelength、content-type等信息
     *
     * @param path
     * @param type
     */
    void addBaseMedia(String path, String type);

    /**
     * 获取永久素材
     *
     * @param mediaId 要获取的素材的media_id
     */
    Map<String, Object> get(String mediaId);

    /**
     * 删除永久素材
     *
     * @param mediaId 要删除的素材的media_id
     */
    void del(String mediaId);


    /**
     * 修改永久图文素材
     *
     * @param bm
     */
    void update(WeixinBaseMediaNews bm);

    /**
     * 获取素材总数
     * 1.永久素材的总数，也会计算公众平台官网素材管理中的素材
     * 2.图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
     */
    void queryMediaCount();


    /**
     * 获取素材列表
     *
     * @param type   是	素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 是	从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  是	返回素材的数量，取值在1到20之间
     * @return
     */
    List<Object> queryMediaNews(String type, int offset, int count);
}
