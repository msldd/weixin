package com.hust.weixin.web.filter;

import com.hust.weixin.entity.User;
import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.model.WeixinFinalUserValue;
import com.hust.weixin.service.IUserService;
import com.hust.weixin.util.SpringBeanFactory;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by Administration on 2016/6/23.
 */
public class WeixinAuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(WeixinAuthFilter.class);
    public static String server_url;

    static {
        Properties prop = new Properties();
        try {
            prop.load(WeixinAuthFilter.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        server_url = prop.getProperty("server_url");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("WeixinAuthFilter开始过滤：" + System.currentTimeMillis());
        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;
        User tu = (User) hRequest.getSession().getAttribute("user");
        logger.info("用户session:" + tu);
        if (tu == null) {
            String agent = hRequest.getHeader("User-Agent");
            if (agent != null && agent.toLowerCase().indexOf("micromessenger") >= 0) {
                String code = request.getParameter("code");
                String state = request.getParameter("state");
                if (code != null && state != null && state.equals("1")) {
                    //通过Code获取openid来进行授权
                    IUserService userService = (IUserService) SpringBeanFactory.getBean("userService");
                    String openid = userService.queryOpenidByCode(code);
                    if (openid != null) {
                        User u = userService.loadByOpenid(openid);
                        if (u == null) {
                            u = userService.queryByOpenid(openid).getUser();
                            userService.add(u);
                        } else {
                            if (u.getStatus() == 0) {
                                u.setStatus(1);
                                userService.update(u);
                            }
                        }
                        hRequest.getSession().setAttribute("user", u);
                    }
                } else {
                    String path = server_url + hRequest.getRequestURI();
                    String query = hRequest.getQueryString();
                    if (query != null) {
                        path = path + "?" + query;
                    }
                    String uri = WeixinFinalUserValue.OAUTH_URL;
                    uri = uri.replace("APPID", WeixinFinalBasicValue.APPID)
                            .replace("REDIRECT_URI", URLEncoder.encode(path, "UTF-8"))
                            .replace("SCOPE", "snsapi_base")
                            .replace("STATE", "1");
                    hResponse.sendRedirect(uri);
                    return;
                }
            }
        }
        chain.doFilter(hRequest, hResponse);
        logger.info("WeixinAuthFilter结束过滤：" + System.currentTimeMillis());
    }

    @Override
    public void destroy() {

    }
}
