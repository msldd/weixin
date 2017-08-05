package com.hust.weixin.kit.msg;

import com.hust.weixin.model.DuplicateMessage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接受消息排重
 * Created by Administration on 2016/6/22.
 */
public class DuplicateMessageKit {
    private static final Logger logger = Logger.getLogger(DuplicateMessageKit.class);
    public static final List<DuplicateMessage> MSGG = new ArrayList<>();

    public static boolean checkDuplicate(Map<String, String> msgMap) {
        DuplicateMessage dm = initDuplicateMessage(msgMap);
        if (MSGG.contains(dm)) {
            return true;
        } else {
            MSGG.add(dm);
            return false;
        }
    }

    public static void setRel(Map<String, String> msgMap, String rel) {
        DuplicateMessage dm = initDuplicateMessage(msgMap);
        DuplicateMessage tdm = MSGG.get(MSGG.indexOf(dm));
        tdm.setRel(rel);
    }

    public static String getRel(Map<String, String> msgMap) {
        DuplicateMessage dm = initDuplicateMessage(msgMap);
        DuplicateMessage tdm = MSGG.get(MSGG.indexOf(dm));
        return tdm.getRel();
    }

    private static DuplicateMessage initDuplicateMessage(Map<String, String> msgMap) {
        String fromUserName = msgMap.get("FromUserName");
        String createTime = msgMap.get("CreateTime");
        return new DuplicateMessage(fromUserName, createTime);
    }

    public static void clear() {
        logger.info("清空开始，之前：" + MSGG.size() + MSGG);
        DuplicateMessage dm;
        for (int i = 0; i < MSGG.size(); i++) {
            dm = MSGG.get(i);
            if ((System.currentTimeMillis() - dm.getCurTime()) / 1000 > 30) {
                MSGG.remove(dm);
            }
        }
        logger.info("清空结束，之后：" + MSGG.size() + MSGG);
    }
}
