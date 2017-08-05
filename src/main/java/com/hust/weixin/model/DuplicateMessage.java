package com.hust.weixin.model;

/**
 * Created by Administration on 2016/6/22.
 */
public class DuplicateMessage {
    private String fromUserName;
    private String createTime;
    private Long curTime;
    private String rel;

    public DuplicateMessage() {
    }

    public DuplicateMessage(String fromUserName, String createTime) {
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.curTime = System.currentTimeMillis();
        this.rel = null;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCurTime() {
        return curTime;
    }

    public void setCurTime(Long curTime) {
        this.curTime = curTime;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DuplicateMessage that = (DuplicateMessage) o;

        if (fromUserName != null ? !fromUserName.equals(that.fromUserName) : that.fromUserName != null) return false;
        return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;

    }

    @Override
    public int hashCode() {
        int result = fromUserName != null ? fromUserName.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DuplicateMessage{" +
                "fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", curTime=" + curTime +
                ", rel='" + rel + '\'' +
                '}';
    }
}
