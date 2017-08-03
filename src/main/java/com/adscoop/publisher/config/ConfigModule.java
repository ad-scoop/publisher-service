package com.adscoop.publisher.config;

import com.adscoop.publisher.handlers.BannerPusherHandler;
import com.adscoop.publisher.handlers.CorsHandler;
import com.adscoop.publisher.handlers.DemoBannerPusherHandler;
import com.adscoop.publisher.handlers.TestHandler;
import com.adscoop.publisher.modules.ServiceCommonConfigModule;
import com.adscoop.publisher.services.BannerPusherCreatorService;
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
    }
}
