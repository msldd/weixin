package com.hust.weixin.quartz;

import com.hust.weixin.kit.msg.DuplicateMessageKit;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administration on 2016/6/22.
 */
public class ClearDuplicateMessageJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DuplicateMessageKit.clear();
    }
}
