package com.hust.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @auther Xiao Xuwen
 * @time 2016/12/22 10:32
 * @verson v1.0
 */
@Controller
@RequestMapping(value = "/app")
public class VersionController {
    public static int downloadCount = 0;

    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        try {

            String downFilename = "/app/v1.apk";
            String filepath = request.getSession().getServletContext().getRealPath("");
            response.setContentType("text/plain");
            response.setHeader("Location", downFilename);
            response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
            System.out.println(filepath + downFilename);
            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = new FileInputStream(filepath + downFilename);

            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            downloadCount++;
            System.out.println("已下载数量：" + downloadCount);
        } catch (FileNotFoundException e1) {
            System.out.println("没有找到您要的文件");
        } catch (Exception e) {
        }

    }
}
