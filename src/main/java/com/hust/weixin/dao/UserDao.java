package com.hust.weixin.dao;

import com.hust.weixin.entity.User;
import com.hust.weixin.hibernate.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public User loadByUsername(String username) {
        return (User) super.queryObject("from User where username=?", username);
    }

    @Override
    public User loadByOpenId(String openid) {
        return (User) super.queryObject("from User where openid=?", openid);
    }

    @Override
    public List<User> list() {
        return super.list("from User");
    }

}
