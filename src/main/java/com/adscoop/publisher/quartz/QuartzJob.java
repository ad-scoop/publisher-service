package com.adscoop.publisher.quartz;

import com.adscoop.publisher.services.BannerPusherCreatorService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;


public class QuartzJob implements Job {


    @Inject
    public QuartzJob(BannerPusherCreatorService bannerPusherCreatorService) {
        this.bannerPusherCreatorService = bannerPusherCreatorService;
    }

    private BannerPusherCreatorService bannerPusherCreatorService;



    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
