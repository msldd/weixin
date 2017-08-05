package com.hust.weixin.kit;

import com.fasterxml.jackson.databind.JsonNode;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administration on 2016/5/8.
 */
public class WeixinBasicKit {
    public static String sha1(String str) {
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha1");
            md.update(str.getBytes());
            byte[] msg = md.digest();
            for (byte b : msg) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String replaceAccessTokenUrl(String url) {
        return url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof String) {
            if ("".equals(((String) obj).trim())) return true;
        }
        if (obj instanceof Integer) {
            if ((Integer) obj == 0) return true;
        }
        return false;
    }

    /**
     * 检查请求是否成功
     *
     * @return
     */
    public static boolean checkRequestSucc(String content) {
        try {
            JsonNode jn = JsonUtil.getMapper().readTree(content);
            if (!jn.has("errcode")) return true;
            if (jn.get("errcode").asInt() == 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getRequestCode(String content) {
        try {
            JsonNode jn = JsonUtil.getMapper().readTree(content);
            if (jn.has("errcode")) return jn.get("errcode").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getRequestMsg(String content) {
        try {
            JsonNode jn = JsonUtil.getMapper().readTree(content);
            if (jn.has("errmsg")) return jn.get("errmsg").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendGet(String url) {
        HttpGet get = null;
        CloseableHttpResponse resp = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            get = new HttpGet(url);
            resp = client.execute(get);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                HttpEntity entity = resp.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                return content;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resp != null) resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String sendJsonPost(String url, String content) {
        return sendPost(url, content, "application/json");
    }

    public static String sendXmlPost(String url, String content) {
        return sendPost(url, content, "application/xml");
    }

    public static String sendPost(String url, String content, String type) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", type);
            StringEntity entity = new StringEntity(content, ContentType.create(type, "UTF-8"));
            post.setEntity(entity);
            resp = client.execute(post);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                String str = EntityUtils.toString(resp.getEntity(), "utf-8");
                return str;
            }
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
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
