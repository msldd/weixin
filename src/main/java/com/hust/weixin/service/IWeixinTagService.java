package com.hust.weixin.service;

import com.hust.weixin.json.Tag;
import com.hust.weixin.json.TagUser;

import java.util.List;

/**
 * Created by Administration on 2016/6/21.
 */
public interface IWeixinTagService {
    /**
     * 创建标签
     *
     * @param tag 标签名（30个字符以内）
     */
    Tag createTag(String tag);

    /**
     * 获取公众号已创建的标签
     *
     * @return
     */
    List<Tag> getTags();

    /**
     * 编辑标签
     *
     * @param tag
     */
    void updateTag(Tag tag);

    /**
     * 删除标签
     * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。
     * 此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。
     *
     * @param id
     */
    void deleteTagById(int id);

    /**
     * 获取标签下粉丝列表
     *
     * @param tid
     * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    TagUser getTagUsers(int tid, String next_openid);

    /**
     * 批量为用户打标签
     *
     * @param tid
     * @param openid_list 粉丝列表
     */
    void batchtagging(int tid, List<String> openid_list);

    /**
     * 批量为用户取消标签
     *
     * @param tid
     * @param openid_list 粉丝列表
     */
    void batchuntagging(int tid, List<String> openid_list);

    /**
     * 获取用户身上的标签列表
     *
     * @param openid
     * @return
     */
    List<Integer> getUserTagIds(String openid);
}
