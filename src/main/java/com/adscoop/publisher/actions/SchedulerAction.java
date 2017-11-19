package com.adscoop.publisher.actions;

import com.adscoop.publisher.services.BannerPusherCreatorService;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import ratpack.rx.RxRatpack;
import rx.Scheduler;
import rx.functions.Action;
import rx.functions.Action0;

import javax.inject.Inject;



@Singleton
@Slf4j
public class SchedulerAction {


    private BannerPusherCreatorService _bannerPusherCreatorService;

    @Inject
    public  SchedulerAction(BannerPusherCreatorService bannerPusherCreatorService) {
        this._bannerPusherCreatorService = bannerPusherCreatorService;
    }


    public void startScheduler() {
                              this._bannerPusherCreatorService.getBannersByTimer();


    }


}
