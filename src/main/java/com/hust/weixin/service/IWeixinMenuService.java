package com.hust.weixin.service;

import com.hust.weixin.json.WeixinMenuDto;
import com.hust.weixin.entity.WeixinMenu;

import java.util.List;

/**
 * Created by Administration on 2016/6/21.
 */
public interface IWeixinMenuService {

    /*************************/
    /***与系统管理相关的方法***/
    /*************************/

    /**
     * 添加菜单
     *
     * @param wm
     */

    void add(WeixinMenu wm);

    /**
     * 删除菜单
     *
     * @param id
     */
    void delete(int id);

    /**
     * 修改菜单
     *
     * @param wm
     */
    void update(WeixinMenu wm);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    WeixinMenu load(int id);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<WeixinMenu> listAll();

    /**
     * @param key
     * @return
     */
    WeixinMenu loadByKey(String key);

    /**
     * 生成菜单列表
     *
     * @return
     */
    List<WeixinMenuDto> generateWeixinMenuDto();

    /*****************************/
    /***与微信平台接口相关的方法***/
    /*****************************/

    /**
     * 发布生成菜单
     */
    void publishMenu();

    /**
     * 查询发布生成的菜单
     *
     * @return
     */
    String queryMenu();

}
