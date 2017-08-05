package com.hust.weixin.dao;

import com.hust.weixin.hibernate.dao.IBaseDao;
import com.hust.weixin.entity.WeixinMenu;

import java.util.List;

/**
 * Created by Administration on 2016/6/21.
 */
public interface IWeixinMenuDao extends IBaseDao<WeixinMenu> {
    WeixinMenu loadByKey(String key);

    List<WeixinMenu> listAll();
}
