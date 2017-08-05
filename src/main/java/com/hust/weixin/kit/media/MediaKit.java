package com.hust.weixin.kit.media;

import com.hust.weixin.json.WeixinTempMedia;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalMediaValue;
import com.hust.weixin.util.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administration on 2016/6/20.
 */
public class MediaKit {
    public static String postMedia(String path, String type) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            client = HttpClients.createDefault();
            String url = WeixinFinalMediaValue.MEDIA_TEMP_UPLOAD;
            url = url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
            url = url.replace("TYPE", type);
            HttpPost post = new HttpPost(url);
            FileBody fb = new FileBody(new File(path));
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("media", fb).build();
            post.setEntity(reqEntity);
            resp = client.execute(post);
            int sc = resp.getStatusLine().getStatusCode();
            if (sc >= 200 && sc < 300) {
                String json = EntityUtils.toString(resp.getEntity(), "UTF-8");
                WeixinTempMedia wm = (WeixinTempMedia) JsonUtil.getInstance().json2obj(json, WeixinTempMedia.class);
                return wm.getMedia_id();
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
        return null;
    }

    public static void getMedia(String mediaId, File f) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            client = HttpClients.createDefault();
            String url = WeixinFinalMediaValue.MEDIA_TEMP_GET;
            url = url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
            url = url.replace("MEDIA_ID", mediaId);
            HttpGet get = new HttpGet(url);
            resp = client.execute(get);
            int sc = resp.getStatusLine().getStatusCode();
            if (sc >= 200 && sc < 300) {
                InputStream is = resp.getEntity().getContent();
                FileUtils.copyInputStreamToFile(is, f);
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
    }
}
