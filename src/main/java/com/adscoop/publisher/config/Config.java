package com.adscoop.publisher.config;

import com.adscoop.publisher.services.BannerNodeService;
import com.adscoop.publisher.services.BannerNodeServiceImpl;
import com.google.inject.AbstractModule;
import com.adscoop.publisher.handlers.BannerPusherHandler;
import com.adscoop.publisher.handlers.CorsHandler;

/**
 * Created by thokle on 05/09/2016.
 */
public class Config extends AbstractModule {
    @Override
    protected void configure() {

        bind(BannerPusherHandler.class);

        bind(CorsHandler.class).asEagerSingleton();

        bind(JsonUtil.class).asEagerSingleton();
bind(BannerNodeService.class).to(BannerNodeServiceImpl.class).asEagerSingleton();

    }
}
