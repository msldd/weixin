package com.hust.weixin.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


public class HttpClientUtil {
    private static final Logger logger = Logger.getLogger(HttpClientUtil.class);
    public static String APPLICATION_JSON = "application/json";

    public static String postMsgToService(String url, String json) {
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost post;
        StringEntity se;
        try {
            httpClient = HttpClients.createDefault();
            post = new HttpPost(url);
            post.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            se = new StringEntity(json, "utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            post.setEntity(se);
            response = httpClient.execute(post);
            int sc = response.getStatusLine().getStatusCode();
            logger.info("响应状态码：" + sc);
            if (sc >= 200 && sc <= 300) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("收到结果字符串为：" + result);
        return result;
    }

    public static String getMsgToService(String url) {
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet get;
        try {
            httpClient = HttpClients.createDefault();
            get = new HttpGet(url);
            response = httpClient.execute(get);
            int sc = response.getStatusLine().getStatusCode();
            logger.info("响应状态码：" + sc);
            if (sc >= 200 && sc <= 300) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (response != null) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("收到结果字符串为：" + result);
        return result;
    }

    public static String postMedia(String url, String path) {
        String result = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        HttpPost post;
        try {
            client = HttpClients.createDefault();
            post = new HttpPost(url);
            FileBody fb = new FileBody(new File(path));
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("media", fb).build();
            post.setEntity(reqEntity);
            resp = client.execute(post);
            int sc = resp.getStatusLine().getStatusCode();
            if (sc >= 200 && sc < 300) {
                result = EntityUtils.toString(resp.getEntity(), "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (resp != null) resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static InputStream getMedia(String url) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            resp = client.execute(get);
            int sc = resp.getStatusLine().getStatusCode();
            if (sc >= 200 && sc < 300) {
                InputStream is = resp.getEntity().getContent();
                return is;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (resp != null) resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
