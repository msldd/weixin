package com.hust.weixin.json;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象
 * Created by Administration on 2016/6/21.
 */
public class WeixinMenuDto {
    /**
     * 是否必须：是
     * 说明：菜单标题，不超过16个字节，子菜单不超过40个字节
     */
    private String name;
    /**
     * 是否必须：click等点击类型必须
     * 说明：菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;
    /**
     * 是否必须：是
     * 说明：菜单的响应动作类型
     */
    private String type;
    /**
     * 是否必须：view类型必须
     * 说明：网页链接，用户点击菜单可打开链接，不超过1024字节
     */
    private String url;
    /**
     * 是否必须：否
     * 说明：二级菜单数组，个数应为1~5个
     */
    private List<WeixinMenuDto> sub_button = new ArrayList<>();

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WeixinMenuDto> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WeixinMenuDto> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "WeixinMenuDto{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", sub_button=" + sub_button +
                '}';
    }
}
