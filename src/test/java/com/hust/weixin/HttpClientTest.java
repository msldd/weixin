package com.hust.weixin;

import com.hust.weixin.json.AccessToken;
import com.hust.weixin.json.ModelMsgData;
import com.hust.weixin.json.TemplateMsg;
import com.hust.weixin.kit.media.MediaKit;
import com.hust.weixin.kit.msg.WeixinMessageKit;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalBasicValue;
import com.hust.weixin.model.WeixinFinalMenuValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/5/9.
 */
public class HttpClientTest {
    private static final String ACCESS_TOKEN = "JVsI-ntsFgGv1MBPHfkEPF5txmMmR2p-slnaZb-IqsMnKc_SgbJG1hwCEdJGq5iB9IKKGGpqyv1Pqz9YVph_7Tw-aWEngeWZxbpd7kRUWEsJJy4703SgTWhiK5sWtoh2ZPNiADAIYW";

    /**
     * 测试ACCESS_TOKEN
     *
     * @throws IOException
     */
    @Test
    public void testHttpClient() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String url = WeixinFinalBasicValue.ACCESS_TOKEN_URL;
        url = url.replaceAll("APPID", WeixinFinalBasicValue.APPID);
        url = url.replaceAll("APPSECRET", WeixinFinalBasicValue.APPSECRET);
        System.out.println(url);
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            AccessToken at = (AccessToken) JsonUtil.getInstance().json2obj(content, AccessToken.class);
            System.out.println(at.getAccess_token() + "," + at.getExpires_in());
        }
    }

    /**
     * 测试自定义创建菜单
     */
    @Test
    public void testMenu() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);

        List<WeixinMenu> wms = new ArrayList<WeixinMenu>();
        WeixinMenu wm1 = new WeixinMenu();
        wm1.setId(1);
        wm1.setName("学习网站");
        wm1.setType("view");
        wm1.setUrl("http://www.baidu.com");
        wms.add(wm1);

        WeixinMenu wm2 = new WeixinMenu();
        wm2.setName("测试资源");

        List<WeixinMenu> wm2Sub = new ArrayList<>();
        wm1 = new WeixinMenu();
        wm1.setId(2);
        wm1.setName("事件测试");
        wm1.setType("click");
        wm1.setKey("A0001");
        wm2Sub.add(wm1);

        wm1 = new WeixinMenu();
        wm1.setId(2);
        wm1.setName("扫描测试");
        wm1.setType("pic_sysphoto");
        wm1.setKey("rselfmenu_1_0");
        wm2Sub.add(wm1);
        wm2.setSub_button(wm2Sub);

        wms.add(wm2);

        Map<String, List<WeixinMenu>> maps = new HashMap<>();
        maps.put("button", wms);
        String json = JsonUtil.getInstance().obj2json(maps);
        System.out.println(json);
        String url = WeixinFinalMenuValue.MENU_ADD;
        url = url.replace("ACCESS_TOKEN", WeixinContext.getAccessToken());
        HttpClientUtil.postMsgToService(url, json);
    }

    @Test
    public void testMsg() throws IOException {
        Map<String, String> maps = new HashMap<>();
        maps.put("ToUserName", "gh_41c355184c4b");
        maps.put("FromUserName", "oPBl5xFg-QyLf5N-r-Ksf946vjyQ>>");
        maps.put("CreateTime", "1466402150");
        maps.put("MsgType", "text");
        maps.put("Content", "hello");
        maps.put("MsgId", "6298149277459745257");
        System.out.println(WeixinMessageKit.handlerMsg(maps));
    }

    @Test
    public void testPostMedia() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
//        String str = MediaKit.postMedia("d:/IMG_0140.JPG", "image");
//        System.out.println(str);
//        String str = MediaKit.postMedia("d:/IMG_0153.JPG", "image");
//        System.out.println(str);
        String str = MediaKit.postMedia("d:/IMG_0159.JPG", "image");
        System.out.println(str);
    }

    @Test
    public void testGetMedia() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        MediaKit.getMedia("ljdI1uniNXvHMmehDMYAAhC4Ou034wyGQnaODnsVohNw9mZRBDT41fU5JvwqdKmy", new File("d:/f.jpg"));
    }

    @Test
    public void testPostTemplateMsg() {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        TemplateMsg tm = new TemplateMsg();
        tm.setTouser("oPBl5xFg-QyLf5N-r-Ksf946vjyQ");
        tm.setTemplate_id("yck6ytCNP93dNbeerrO3WFjEqaesfLJKi0rZzWSVe9Q");
        tm.setUrl("http://www.baidu.com");
        tm.setTopcolor("#ff0000");
        Map<String, Object> data = new HashMap<>();
        data.put("num", new ModelMsgData("1", "#173177"));
        tm.setData(data);
        WeixinMessageKit.postTemplateMsg(tm);
    }
}