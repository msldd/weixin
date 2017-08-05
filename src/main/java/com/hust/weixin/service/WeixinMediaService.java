package com.hust.weixin.service;

import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.WeixinBaseMediaNews;
import com.hust.weixin.json.WeixinTempMedia;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinFinalMediaValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/26.
 */
@Service
public class WeixinMediaService implements IWeixinMediaService {
    private static Logger logger = Logger.getLogger(WeixinMediaService.class);

    @Override
    public WeixinTempMedia addTempMedia(String type, String path) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalMediaValue.MEDIA_TEMP_UPLOAD);
        url = url.replace("TYPE", type);
        String json = HttpClientUtil.postMedia(url, path);
        try {
            WeixinTempMedia tm = (WeixinTempMedia) JsonUtil.getInstance().json2obj(json, WeixinTempMedia.class);
            logger.info("添加临时素材成功：" + tm);
            return tm;
        } catch (Exception e) {
            throw new SysException("添加临时素材异常");
        }
    }

    @Override
    public void queryTempMedia(String media_id, String dir) {

    }

    @Override
    public String addBaseMediaNews(WeixinBaseMediaNews bm) {

        return null;
    }

    @Override
    public String uploadImg(String path) {

        return null;
    }

    @Override
    public void addBaseMedia(String path, String type) {

    }

    @Override
    public Map<String, Object> get(String mediaId) {
        return null;
    }

    @Override
    public void del(String mediaId) {

    }

    @Override
    public void update(WeixinBaseMediaNews bm) {

    }

    @Override
    public void queryMediaCount() {

    }

    @Override
    public List<Object> queryMediaNews(String type, int offset, int count) {
        return null;
    }
}
