package com.hust.weixin.controller;

import com.hust.weixin.entity.WeixinQr;
import com.hust.weixin.service.IWeixinQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wqr")
public class WeixinQrController {
    @Autowired
    private IWeixinQrService weixinQrService;

    @RequestMapping("/list/{type}")
    public String list(@PathVariable String type, Model model) {
        if ("temp".equals(type)) {
            model.addAttribute("datas", weixinQrService.listTempQr());
        } else {
            model.addAttribute("datas", weixinQrService.listBaseQr());
        }
        return "wqr/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("wq", new WeixinQr());
        return "wqr/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(WeixinQr wq) {
        wq.setStatus(1);
        weixinQrService.add(wq);
        return "redirect:/wqr/list/base";
    }
}
