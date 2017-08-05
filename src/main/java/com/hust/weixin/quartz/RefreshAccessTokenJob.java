package com.hust.weixin.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administration on 2016/5/9.
 */
public class RefreshAccessTokenJob extends QuartzJobBean {

    private RefreshAccessTokenTask refreshAccessTokenTask;

    public void setRefreshAccessTokenTask(RefreshAccessTokenTask refreshAccessTokenTask) {
        this.refreshAccessTokenTask = refreshAccessTokenTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        refreshAccessTokenTask.refreshToken();
    }
}
