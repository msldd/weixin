package com.hust.weixin.json;

/**
 * Created by Administration on 2016/6/25.
 */
public class WeixinKfOnline {
    private String kf_account;      //完整客服帐号，格式为：帐号前缀@公众号微信号
    private String status;          //客服在线状态，目前为：1、web 在线
    private String kf_id;           //客服编号
    private String accepted_case;   //客服当前正在接待的会话数

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKf_id() {
        return kf_id;
    }

    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }

    public String getAccepted_case() {
        return accepted_case;
    }

    public void setAccepted_case(String accepted_case) {
        this.accepted_case = accepted_case;
    }

    @Override
    public String toString() {
        return "WeixinKfOnline{" +
                "kf_account='" + kf_account + '\'' +
                ", status='" + status + '\'' +
                ", kf_id='" + kf_id + '\'' +
                ", accepted_case='" + accepted_case + '\'' +
                '}';
    }
}
