package com.adscoop.publisher.config;

import com.adscoop.publisher.handlers.CampaginHandler;
import com.adscoop.publisher.jobs.BannerPusherHandler;
import com.adscoop.publisher.handlers.CorsHandler;
import com.adscoop.publisher.modules.ServiceCommonConfigModule;
import com.adscoop.publisher.services.BannerNodeService;
import com.adscoop.publisher.services.BannerNodeServiceImpl;
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
        bind(BannerNodeService.class).to(BannerNodeServiceImpl.class).asEagerSingleton();
        bind(ServiceCommonConfigModule.class).asEagerSingleton();
        bind(CampaginHandler.class).asEagerSingleton();

    }
}
