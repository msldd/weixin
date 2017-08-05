package com.hust.weixin.json;

/**
 * 临时素材
 * Created by Administration on 2016/6/20.
 */
public class WeixinTempMedia {
    private String type;        //媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
    private String media_id;    //媒体文件上传后，获取时的唯一标识
    private String created_at;  //媒体文件上传时间戳

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "WeixinTempMedia{" +
                "type='" + type + '\'' +
                ", media_id='" + media_id + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
