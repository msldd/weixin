package com.hust.weixin.dao;

import com.hust.weixin.entity.User;
import com.hust.weixin.hibernate.dao.IBaseDao;

import java.util.List;


public interface IUserDao extends IBaseDao<User> {
    User loadByUsername(String username);

    User loadByOpenId(String openid);

    List<User> list();
}
