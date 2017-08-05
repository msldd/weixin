package com.hust.weixin.service;

import com.hust.weixin.entity.User;
import com.hust.weixin.json.WUser;

import java.util.List;


public interface IUserService {
    /*************************/
    /***与系统管理相关的方法***/
    /*************************/

    /**
     * @param user
     */
    public void add(User user);

    /**
     * @param user
     */
    public void update(User user);

    /**
     * @param id
     */
    public void delete(int id);

    /**
     * @param id
     * @return
     */
    public User load(int id);

    /**
     * @param username
     * @return
     */
    public User loadByUsername(String username);

    /**
     * @param openid
     * @return
     */
    public User loadByOpenid(String openid);

    /**
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * @return
     */
    public List<User> list();

    /*****************************/
    /***与微信平台接口相关的方法***/
    /*****************************/
    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param openid
     * @return
     */
    public WUser queryByOpenid(String openid);

    /**
     * 通过code换取网页授权access_token
     *
     * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
     * @return
     */
    public String queryOpenidByCode(String code);
}
