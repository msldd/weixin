package com.hust.weixin.controller;

import com.hust.weixin.json.Tag;
import com.hust.weixin.service.IWeixinTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administration on 2016/6/24.
 */
@Controller
@RequestMapping("wtag")
public class WeixinTagController {
    @Autowired
    private IWeixinTagService weixinTagService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("datas", weixinTagService.getTags());
        return "wtag/list";
    }

    @RequestMapping("/query/{openid}")
    public String list(@PathVariable String openid, Model model) {
        model.addAttribute("wg", weixinTagService.getUserTagIds(openid));
        return "wtag/query";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("wg", new Tag());
        return "wtag/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Tag tag) {
        weixinTagService.createTag(tag.getName());
        return "redirect:/wtag/list";
    }
}
