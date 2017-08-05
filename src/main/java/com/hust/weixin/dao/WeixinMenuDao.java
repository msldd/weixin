package com.hust.weixin.dao;

import com.hust.weixin.entity.WeixinMenu;
import com.hust.weixin.hibernate.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administration on 2016/6/21.
 */
@Repository
public class WeixinMenuDao extends BaseDao<WeixinMenu> implements IWeixinMenuDao {
    @Override
    public WeixinMenu loadByKey(String key) {
        return (WeixinMenu) super.queryObject("from WeixinMenu where menuKey=?", key);
    }

    @Override
    public List<WeixinMenu> listAll() {
        return super.list("from WeixinMenu");
    }
}
