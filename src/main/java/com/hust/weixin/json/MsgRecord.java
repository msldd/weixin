package com.hust.weixin.json;

/**
 * 聊天记录
 * Created by Administration on 2016/6/25.
 */
public class MsgRecord {
    private String worker;      //完整客服帐号，格式为：帐号前缀@公众号微信号
    private String openid;      //用户标识
    private int opercode;       //操作码，2002（客服发送信息），2003（客服接收消息）
    private String text;        //聊天记录
    private long time;          //操作时间，unix时间戳

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getOpercode() {
        return opercode;
    }

    public void setOpercode(int opercode) {
        this.opercode = opercode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MsgRecord{" +
                "worker='" + worker + '\'' +
                ", openid='" + openid + '\'' +
                ", opercode=" + opercode +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
