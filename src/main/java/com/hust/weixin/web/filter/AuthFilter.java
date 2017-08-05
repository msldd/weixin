package com.hust.weixin.web.filter;

import com.hust.weixin.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        logger.info("AuthFilter开始过滤：" + System.currentTimeMillis());
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String url = httpReq.getRequestURI();
        if (url.indexOf("node_modules") > 0 || url.indexOf("resources") > 0 || url.indexOf("login") > 0 || url.indexOf("/wget") > 0) {
            chain.doFilter(httpReq, httpResp);
            return;
        } else {
            User u = (User) httpReq.getSession().getAttribute("user");
            if (u == null) {
                httpResp.sendRedirect(httpReq.getContextPath() + "/user/login");
                return;
            }
            chain.doFilter(httpReq, httpResp);
        }
        logger.info("AuthFilter结束过滤：" + System.currentTimeMillis());
    }

    @Override
    public void init(FilterConfig cfg) throws ServletException {

    }

}
