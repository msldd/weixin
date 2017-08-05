package com.hust.weixin.service;

import com.hust.weixin.entity.WeixinQr;

import java.util.List;

/**
 * Created by Administration on 2016/6/24.
 */
public interface IWeixinQrService {
    /*************************/
    /***与系统管理相关的方法***/
    /*************************/

    /**
     * @param qr
     */
    WeixinQr add(WeixinQr qr);

    /**
     * @param id
     */
    void delete(String id);

    /**
     * @param id
     * @return
     */
    WeixinQr load(String id);

    /**
     * @param qr
     */
    void update(WeixinQr qr);

    /**
     * 获取永久的二维码
     *
     * @return
     */
    List<WeixinQr> listBaseQr();

    /**
     * 获取临时的二维码
     *
     * @return
     */
    List<WeixinQr> listTempQr();

    /**
     * 根据场景值获得二维码
     *
     * @param snum
     * @return
     */
    WeixinQr loadBySnum(int snum);

    /*****************************/
    /***与微信平台接口相关的方法***/
    /*****************************/

    /**
     * 获得永久二维码ticket
     *
     * @param snum
     * @return
     */
    public String loadTicketByBaseQr(int snum);

    /**
     * 获得临时二维码ticket
     *
     * @param snum
     * @return
     */
    public String loadTicketByTempQr(int snum);
}
