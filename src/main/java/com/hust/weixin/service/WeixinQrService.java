package com.hust.weixin.service;

import com.hust.weixin.dao.IWeixinQrDao;
import com.hust.weixin.entity.WeixinQr;
import com.hust.weixin.exception.SysException;
import com.hust.weixin.kit.WeixinBasicKit;
import com.hust.weixin.model.WeixinContext;
import com.hust.weixin.model.WeixinFinalQrValue;
import com.hust.weixin.util.HttpClientUtil;
import com.hust.weixin.util.JsonUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administration on 2016/6/24.
 */
@Service
public class WeixinQrService implements IWeixinQrService {

    @Autowired
    private IWeixinQrDao weixinQrDao;

    @Override
    public WeixinQr add(WeixinQr qr) {
        if (qr.getSnum() == null) throw new SysException("场景值不能为空");
        if (qr.getSnum() <= WeixinQr.MAX_BASE_SNUM) {
            //永久二维码
            WeixinQr baseqr = this.loadBySnum(qr.getSnum());
            if (baseqr != null) throw new SysException("固定二维码的场景值已经存在");
            qr.setCreateDate(new Date());
            String ticket = loadTicketByBaseQr(qr.getSnum());
            if (ticket == null) throw new SysException("获取永久二维码失败");
            if (ticket != null && !"".equals(ticket)) {
                qr.setTicket(ticket);
            }
            weixinQrDao.add(qr);
        } else {
            //临时二维码
            return addTempQr(qr);
        }
        return qr;
    }

    private WeixinQr addTempQr(WeixinQr qr) {
        WeixinQr tempqr = this.loadBySnum(qr.getSnum());
        if (tempqr == null) {
            qr.setCreateDate(new Date());
            setTempQrTicket(qr);
            weixinQrDao.add(qr);
            return qr;
        } else {
            if (checkExpired(tempqr)) {
                //1、先删除tempqr,之后再添加qr
                //2、更新tempqr
                tempqr.setCreateDate(new Date());
                tempqr.setStatus(qr.getStatus());
                tempqr.setName(qr.getName());
                tempqr.setMsg(qr.getMsg());
                tempqr.setQrData(qr.getQrData());
                tempqr.setSnum(qr.getSnum());
                tempqr.setType(qr.getType());
                setTempQrTicket(tempqr);
                weixinQrDao.update(tempqr);
                return tempqr;
            } else {
                qr.setSnum((WeixinQr.MAX_BASE_SNUM + 1) + RandomUtils.nextInt());
                return addTempQr(qr);
            }
        }
    }

    private void setTempQrTicket(WeixinQr qr) {
        String ticket = loadTicketByTempQr(qr.getSnum());
        if (ticket == null) throw new SysException("获取临时二维码失败");
        if (ticket != null && !"".equals(ticket)) {
            qr.setTicket(ticket);
        }
    }

    /**
     * 检查二维码是否过期 时间60s
     *
     * @param tempqr
     * @return
     */
    private boolean checkExpired(WeixinQr tempqr) {
        long t = System.currentTimeMillis() - tempqr.getCreateDate().getTime();
        if ((t / 1000) > 60) return true;
        return false;
    }

    @Override
    public void delete(String id) {
        weixinQrDao.delete(id);
    }

    @Override
    public WeixinQr load(String id) {
        return weixinQrDao.load(id);
    }

    @Override
    public void update(WeixinQr qr) {
        weixinQrDao.update(qr);
    }

    @Override
    public List<WeixinQr> listBaseQr() {
        return weixinQrDao.listBaseQr();
    }

    @Override
    public List<WeixinQr> listTempQr() {
        return weixinQrDao.listTempQr();
    }

    @Override
    public WeixinQr loadBySnum(int snum) {
        return weixinQrDao.loadBySnum(snum);
    }

    @Override
    public String loadTicketByBaseQr(int snum) {
        String url = WeixinFinalQrValue.QR_TICKET;
        url = url.replace("TOKEN", WeixinContext.getAccessToken());
        String json = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + snum + "}}}";
        String rjson = HttpClientUtil.postMsgToService(url, json);
        if (WeixinBasicKit.checkRequestSucc(rjson)) {
            try {
                return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String loadTicketByTempQr(int snum) {
        String url = WeixinFinalQrValue.QR_TICKET;
        url = url.replace("TOKEN", WeixinContext.getAccessToken());
        String json = "{\"expire_seconds\": 180, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + snum + "}}}";
        String rjson = HttpClientUtil.postMsgToService(url, json);
        if (WeixinBasicKit.checkRequestSucc(rjson)) {
            try {
                return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
