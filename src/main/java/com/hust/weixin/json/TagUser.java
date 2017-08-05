package com.hust.weixin.json;

import java.util.List;
import java.util.Map;

/**
 * Created by Administration on 2016/6/21.
 */
public class TagUser {
    private int count;
    private Map<String, List<String>> data;
    private String next_openid;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    @Override
    public String toString() {
        return "TagUser{" +
                "count=" + count +
                ", data=" + data +
                ", next_openid='" + next_openid + '\'' +
                '}';
    }
}
