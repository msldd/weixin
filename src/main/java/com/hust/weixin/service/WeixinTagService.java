package com.hust.weixin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.json.ErrorEntity;
import com.hust.weixin.json.Tag;
import com.hust.weixin.json.TagUser;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinFinalUserValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/21.
 */
@Service
public class WeixinTagService implements IWeixinTagService {
    private static Logger logger = Logger.getLogger(WeixinTagService.class);

    @Override
    public Tag createTag(String tag) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_CREATE);
        Map<String, Tag> map = new HashMap<>();
        map.put("tag", new Tag(tag));
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        try {
            int id = JsonUtil.getMapper().readTree(json).get("tag").get("id").asInt();
            String name = JsonUtil.getMapper().readTree(json).get("tag").get("name").asText();
            logger.info("创建用户标签成功：" + new Tag(id, name));
            return new Tag(id, name);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new SysException("创建用户标签异常");
        }
        return null;
    }

    @Override
    public List<Tag> getTags() {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_GET);
        String json = HttpClientUtil.getMsgToService(url);
        try {
            Map<String, List<Map>> maps = (Map<String, List<Map>>) JsonUtil.getInstance().json2obj(json, Map.class);
            List<Map> listMap = maps.get("tags");
            List<Tag> tags = new ArrayList<>();
            for (Map m : listMap) {
                Tag t = new Tag();
                t.setId((Integer) m.get("id"));
                t.setName((String) m.get("name"));
                t.setCount((Integer) m.get("count"));
                tags.add(t);
            }
            logger.info("获取公众号已创建的标签成功：" + tags);
            return tags;
        } catch (Exception e) {
            throw new SysException("获取公众号已创建的标签异常");
        }
    }

    @Override
    public void updateTag(Tag tag) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_UPDATE);
        Map<String, Tag> map = new HashMap<>();
        map.put("tag", tag);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("编辑标签异常：" + err);
        } else {
            logger.info("编辑标签成功:" + err);
        }
    }

    @Override
    public void deleteTagById(int id) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_DELETE);
        Map<String, Tag> map = new HashMap<>();
        map.put("tag", new Tag(id));
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("删除标签异常：" + err);
        } else {
            logger.info("删除标签成功:" + err);
        }
    }

    @Override
    public TagUser getTagUsers(int tid, String next_openid) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_USER_GET);
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tid);
        map.put("next_openid", next_openid);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        try {
            TagUser tu = (TagUser) JsonUtil.getInstance().json2obj(json, TagUser.class);
            logger.info("获取标签下粉丝列表成功:" + tu);
            return tu;
        } catch (Exception e) {
            throw new SysException("获取标签下粉丝列表异常");
        }

    }

    @Override
    public void batchtagging(int tid, List<String> openid_list) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_USER_BATCHTAGGING);
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tid);
        map.put("openid_list", openid_list);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("批量为用户打标签异常：" + err);
        } else {
            logger.info("批量为用户打标签成功:" + err);
        }
    }

    @Override
    public void batchuntagging(int tid, List<String> openid_list) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.TAG_USER_BATCHUNTAGGING);
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tid);
        map.put("openid_list", openid_list);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        ErrorEntity err = (ErrorEntity) JsonUtil.getInstance().json2obj(json, ErrorEntity.class);
        if (!err.getErrcode().equals("0")) {
            throw new SysException("批量为用户取消标签异常：" + err);
        } else {
            logger.info("批量为用户取消标签成功:" + err);
        }
    }

    @Override
    public List<Integer> getUserTagIds(String openid) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalUserValue.USER_TAGIDS);
        Map<String, String> map = new HashMap<>();
        map.put("openid", openid);
        String json = HttpClientUtil.postMsgToService(url, JsonUtil.getInstance().obj2json(map));
        try {
            Map<String, List<Integer>> maps = (Map<String, List<Integer>>) JsonUtil.getInstance().json2obj(json, Map.class);
            List<Integer> tagIds = maps.get("tagid_list");
            logger.info("获取用户身上的标签列表成功：" + tagIds);
            return tagIds;
        } catch (Exception e) {
            throw new SysException("获取用户身上的标签列表异常");
        }
    }
}
