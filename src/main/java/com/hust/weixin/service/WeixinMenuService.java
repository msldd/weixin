package com.hust.weixin.service;

import com.hust.weixin.dao.IWeixinMenuDao;
import com.hust.weixin.entity.WeixinMenu;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.ErrorEntity;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinFinalMenuValue;
import com.hust.weixin.json.WeixinMenuDto;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/21.
 */
@Service
public class WeixinMenuService implements IWeixinMenuService {

    @Autowired
    private IWeixinMenuDao weixinMenuDao;

    @Override
    public void add(WeixinMenu wm) {
        if (wm.getType().equals("click"))
            wm.setMenuKey("KEY_" + System.currentTimeMillis());
        weixinMenuDao.add(wm);
    }

    @Override
    public void delete(int id) {
        weixinMenuDao.delete(id);
    }

    @Override
    public void update(WeixinMenu wm) {
        weixinMenuDao.update(wm);
    }

    @Override
    public WeixinMenu load(int id) {
        return weixinMenuDao.load(id);
    }

    @Override
    public List<WeixinMenu> listAll() {
        return weixinMenuDao.listAll();
    }

    @Override
    public WeixinMenu loadByKey(String key) {
        return weixinMenuDao.loadByKey(key);
    }

    @Override
    public List<WeixinMenuDto> generateWeixinMenuDto() {
        List<WeixinMenu> menus = this.listAll();
        List<WeixinMenuDto> menuDtos = new ArrayList<WeixinMenuDto>();
        WeixinMenuDto wmd = null;
        for (WeixinMenu wm : menus) {
            wmd = new WeixinMenuDto();
            wmd.setKey(wm.getMenuKey());
            wmd.setName(wm.getName());
            wmd.setType(wm.getType());
            wmd.setId(wm.getId());
            wmd.setUrl(wm.getUrl());
            if (wm.getPid() == null || wm.getPid() == 0) {
                if (wmd.getSub_button() == null) {
                    wmd.setSub_button(new ArrayList<WeixinMenuDto>());
                }
                menuDtos.add(wmd);
            } else {
                WeixinMenuDto twmd = findById(wm.getPid(), menuDtos);
                if (twmd == null) throw new RuntimeException("菜单的父类对象有问题，请检查");
                twmd.getSub_button().add(wmd);
            }
        }
        return menuDtos;
    }

    private WeixinMenuDto findById(int id, List<WeixinMenuDto> wmds) {
        for (WeixinMenuDto wmd : wmds) {
            if (wmd.getId() == id) {
                return wmd;
            }
        }
        return null;
    }

    @Override
    public void publishMenu() {
        List<WeixinMenuDto> menuDtos = generateWeixinMenuDto();
        Map<String, List<WeixinMenuDto>> maps = new HashMap<>();
        maps.put("button", menuDtos);
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalMenuValue.MENU_ADD);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(maps));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("发布菜单失败：" + err);
        }
    }

    @Override
    public String queryMenu() {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalMenuValue.MENU_QUERY);
        return HttpClientUtil.getMsgToService(url);
    }
}
