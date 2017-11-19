package com.adscoop.publisher.config;

import com.adscoop.publisher.actions.SchedulerAction;
import com.adscoop.publisher.handlers.*;
import com.adscoop.publisher.modules.ServiceCommonConfigModule;
import com.adscoop.publisher.quartz.QuartzJob;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.adscoop.publisher.services.SchedulerTimerService;
import com.google.inject.AbstractModule;

/**
 * Created by thokle on 05/09/2016.
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BannerPusherHandler.class).asEagerSingleton();
        bind(CorsHandler.class).asEagerSingleton();
        bind(JsonUtil.class).asEagerSingleton();
        bind(ServiceCommonConfigModule.class).asEagerSingleton();
        bind(DemoBannerPusherHandler.class).asEagerSingleton();
        bind(BannerPusherCreatorService.class).asEagerSingleton();
        bind(TestHandler.class).asEagerSingleton();
        bind(SchedulerTimerService.class).asEagerSingleton();
        bind(SchedulerAction.class).asEagerSingleton();
        bind(TimerHandler.class).asEagerSingleton();
        bind(HandlerByToken.class).asEagerSingleton();
    }
}
