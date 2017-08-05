package com.hust.weixin.model;

/**
 * 菜单管理
 * Created by Administration on 2016/6/23.
 */
public class WeixinFinalMenuValue {
    /**
     * 自定义菜单创建接口URL:POST
     */
    public final static String MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 自定义菜单查询接口URL:GET
     */
    public final static String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
}
