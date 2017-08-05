package com.hust.weixin.service;

import com.hust.weixin.dao.IUserDao;
import com.hust.weixin.entity.User;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.WUser;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.model.WeixinFinalUserValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public void add(User user) {
        User u = this.loadByUsername(user.getUsername());
        if (u != null) throw new SysException("用户名已经存在");
        user.setStatus(1);
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public User loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    public User loadByOpenid(String openid) {
        return userDao.loadByOpenId(openid);
    }

    @Override
    public User login(String username, String password) {
        User u = this.loadByUsername(username);
        if (u == null) throw new SysException("用户名不存在!");
        if (!password.equals(u.getPassword())) throw new SysException("用户密码不正确！");
        if (u.getStatus() == 0) throw new SysException("用户已经停用!");
        return u;
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public WUser queryByOpenid(String openid) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.USER_INFO);
        url = url.replace("OPENID", openid);
        String json = HttpClientUtil.getMsgToService(url);
        return (WUser) JsonUtil.getInstance().json2obj(json, WUser.class);
    }

    @Override
    public String queryOpenidByCode(String code) {
        String url = WeixinFinalUserValue.OAUTH_ACCESS_TOKEN;
        url = url.replace("APPID", WeixinFinalBasicValue.APPID)
                .replace("SECRET", WeixinFinalBasicValue.APPSECRET)
                .replace("CODE", code);
        String json = HttpClientUtil.getMsgToService(url);
        try {
            return JsonUtil.getMapper().readTree(json).get("openid").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
