package com.hust.weixin.dao;

import com.hust.weixin.entity.WeixinQr;

import java.util.List;

/**
 * Created by Administration on 2016/6/24.
 */
public interface IWeixinQrDao {
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
}
