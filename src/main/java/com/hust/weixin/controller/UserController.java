package com.hust.weixin.controller;

import com.hust.weixin.entity.User;
import com.hust.weixin.entity.WeixinQr;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.service.IUserService;
import com.hust.weixin.service.IWeixinQrService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IWeixinQrService weixinQrService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user) {
        user.setBind(0);
        userService.add(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        User u = userService.load(id);
        model.addAttribute("user", u);
        return "user/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, User user) {
        User tu = userService.load(id);
        tu.setNickname(user.getNickname());
        tu.setPassword(user.getPassword());
        userService.update(tu);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        User u = userService.login(username, password);
        session.setAttribute("user", u);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/bindNewUser", method = RequestMethod.GET)
    public String bindNewUser(Model model, HttpSession session, HttpServletResponse resp) throws IOException {
        User u = (User) session.getAttribute("user");
        if (u == null) resp.sendRedirect("/user/login");
        if (u.getBind() == 1) return "redirect:/user/list";
        model.addAttribute("user", u);
        return "user/bindNewUser";
    }


    @RequestMapping(value = "/bindNewUser", method = RequestMethod.POST)
    public String bindNewUser(String username, String password, HttpSession session, HttpServletResponse resp) throws IOException {
        User u = (User) session.getAttribute("user");
        if (u == null) resp.sendRedirect("/user/login");
        User tu = userService.load(u.getId());
        tu.setBind(1);
        tu.setUsername(username);
        tu.setPassword(password);
        userService.update(tu);
        session.setAttribute("user", tu);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/bindExistUser", method = RequestMethod.GET)
    public String bindExistUser(HttpSession session, HttpServletResponse resp) throws IOException {
        User u = (User) session.getAttribute("user");
        if (u == null) resp.sendRedirect("/user/login");
        if (u.getBind() == 1) return "redirect:/user/list";
        return "user/bindExistUser";
    }

    @RequestMapping(value = "/bindExistUser", method = RequestMethod.POST)
    public String bindExistUser(String username, String password, HttpSession session, HttpServletResponse resp) throws IOException {
        User u = (User) session.getAttribute("user");
        if (u == null) resp.sendRedirect("/user/login");
        User tu = userService.loadByUsername(username);
        if (tu != null) {
            if (tu.getPassword().equals(password)) {
                tu.setBind(1);
                tu.setImgUrl(u.getImgUrl());
                tu.setSex(u.getSex());
                tu.setStatus(1);
                tu.setNickname(u.getNickname());
                tu.setOpenid(u.getOpenid());
                userService.update(tu);
                userService.delete(u.getId());
                session.setAttribute("user", tu);
            } else {
                throw new SysException("原始的密码输入出错!");
            }
        } else {
            throw new SysException("该用户不存在");
        }

        return "redirect:/user/list";
    }

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
    public String forgetPassword(HttpSession session, Model model) {
        User u = (User) session.getAttribute("user");
        if (u.getBind() == 0) {
            throw new SysException("请绑定用户之后才能在微信中修改密码！");
        }
        model.addAttribute("user", u);
        return "user/forgetPwd";

    }

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
    public String forgetPassword(HttpSession session, String password) {
        User u = (User) session.getAttribute("user");
        if (u.getBind() == 0) {
            throw new SysException("请绑定用户之后才能在微信中修改密码！");
        }
        u.setPassword(password);
        userService.update(u);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "qrlogin")
    public String qrlogin(Model model) {
        WeixinQr qr = generateLoginQr();
        weixinQrService.add(qr);
        model.addAttribute("wq", qr);
        return "user/qrlogin";
    }

    @RequestMapping(value = "checkqrlogin")
    @ResponseBody
    public String checkqrlogin(int snum, HttpSession session, HttpServletResponse response) throws IOException {
        WeixinQr qr = weixinQrService.loadBySnum(snum);
        if (qr != null && qr.getStatus() == 1) {
            String openid = qr.getQrData();
            User u = userService.loadByOpenid(openid);
            session.setAttribute("user", u);
            return "1";
        } else {
            return "0";
        }
    }

    private WeixinQr generateLoginQr() {
        WeixinQr qr = new WeixinQr();
        qr.setName("扫码登录");
        qr.setMsg("扫码登陆");
        qr.setSnum((WeixinQr.MAX_BASE_SNUM + 1) + RandomUtils.nextInt());
        qr.setStatus(0);
        qr.setType(WeixinQr.TEMP_LOGIN);
        return qr;
    }

}
