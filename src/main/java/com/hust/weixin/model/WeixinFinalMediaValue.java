package com.hust.weixin.model;

/**
 * 素材管理
 * Created by Administration on 2016/6/23.
 */
public class WeixinFinalMediaValue {
    /**
     * 新增临时素材URL：POST
     */
    public final static String MEDIA_TEMP_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     * 获取临时素材URL：GET
     */
    public final static String MEDIA_TEMP_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    /**
     * 新增永久图文素材URL：POST
     */
    public final static String MEDIA_BASE_ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    /**
     * 上传图文消息内的图片获取URL：POST
     */
    public final static String MEDIA_UPLOADIMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
    /**
     * 新增其他类型永久素材URL：POST
     */
    public final static String MEDIA_BASE_ADD = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     * 获取永久素材URL：POST
     */
    public final static String MEDIA_BASE_GET = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    /**
     * 删除永久素材URL：POST
     */
    public final static String MEDIA_BASE_DEL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
    /**
     * 修改永久图文素材URL：POST
     */
    public final static String MEDIA_BASE_UPDATE_NEWS = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
    /**
     * 获取素材总数URL：GET
     */
    public final static String MEDIA_BASE_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
    /**
     * 获取素材列表URL：POST
     */
    public final static String MEDIA_BASE_BATCH = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
}
