package com.hust.weixin.service;

import com.hust.weixin.json.Tag;
import com.hust.weixin.json.TagUser;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.service.IWeixinTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administration on 2016/6/21.
 */
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WeixinTagServiceTest {
    private static final String ACCESS_TOKEN = "Yhug_uHDqPKxWEhQRtLSvEebCeNG-FktpAntRPQ1J5EWXJEF7UjTq0UiQ-Mc8wBuURbBPRQ7oboxKtdcaIWGDgvggGRxQQ2e7EZrCB7EkRAOUFgAGAEKV";
    @Autowired
    private IWeixinTagService weoxinTagService;

    @Test
    public void createTag() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        weoxinTagService.createTag("测试组20");
    }

    @Test
    public void getTags() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        List<Tag> tags = weoxinTagService.getTags();
    }

    @Test
    public void updateTag() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        Tag tag = new Tag();
        tag.setId(101);
        tag.setName("测试组2");
        weoxinTagService.updateTag(tag);
    }

    @Test
    public void deleteTag() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        weoxinTagService.deleteTagById(105);
    }

    @Test
    public void getTagUsers() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        TagUser tu = weoxinTagService.getTagUsers(0, "");
        System.out.println(tu);
    }

    @Test
    public void batchtagging() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        List<String> openid_list = new ArrayList<>();
        openid_list.add("oPBl5xFg-QyLf5N-r-Ksf946vjyQ");
        openid_list.add("oPBl5xEO_0FhK56BeHrPHE7vRdd4");
        weoxinTagService.batchtagging(100, openid_list);
    }

    @Test
    public void batchuntagging() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        List<String> openid_list = new ArrayList<>();
        openid_list.add("oPBl5xFg-QyLf5N-r-Ksf946vjyQ");
        weoxinTagService.batchuntagging(100, openid_list);
    }

    @Test
    public void getUserTagIds() throws Exception {
        WeixinContext.setAccessToken(ACCESS_TOKEN);
        List<Integer> tagIds = weoxinTagService.getUserTagIds("oPBl5xFg-QyLf5N-r-Ksf946vjyQ");
        System.out.println(tagIds);
    }
}
