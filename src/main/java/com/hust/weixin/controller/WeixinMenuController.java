package com.hust.weixin.controller;

import com.hust.weixin.entity.WeixinMenu;
import com.hust.weixin.service.IWeixinMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administration on 2016/6/21.
 */

@Controller
@RequestMapping("/weixinMenu")
public class WeixinMenuController {
    @Autowired
    private IWeixinMenuService weixinMenuService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("menus", weixinMenuService.listAll());
        model.addAttribute("wmds", weixinMenuService.generateWeixinMenuDto());
        return "weixinMenu/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("menu", new WeixinMenu());
        return "weixinMenu/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(WeixinMenu menu) {
        weixinMenuService.add(menu);
        return "redirect:/weixinMenu/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("menu", weixinMenuService.load(id));
        return "weixinMenu/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, WeixinMenu menu) {
        WeixinMenu wm = weixinMenuService.load(id);
        wm.setName(menu.getName());
        wm.setType(menu.getType());
        wm.setContent(menu.getContent());
        wm.setMenuKey(menu.getMenuKey());
        wm.setUrl(menu.getUrl());
        wm.setRespType(menu.getRespType());
        weixinMenuService.update(wm);
        return "redirect:/weixinMenu/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Model model) {
        weixinMenuService.delete(id);
        return "redirect:/weixinMenu/list";
    }

    @RequestMapping("/queryPublishMenu")
    public String queryPublish(Model model) {
        model.addAttribute("ms", weixinMenuService.queryMenu());
        return "weixinMenu/publish";
    }

    @RequestMapping("/publishMenu")
    public String publish() {
        weixinMenuService.publishMenu();
        return "redirect:/weixinMenu/queryPublishMenu";
    }
}
