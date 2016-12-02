package com.adscoop.publisher.config;

import com.adscoop.publisher.services.BannerPublisherService;
import com.adscoop.publisher.services.UploadFileService;
import com.google.inject.AbstractModule;
import com.adscoop.publisher.handlers.BannerHandler;
import com.adscoop.publisher.handlers.CorsHandler;

/**
 * Created by thokle on 05/09/2016.
 */
public class Config extends AbstractModule {
    @Override
    protected void configure() {

        bind(BannerHandler.class);

        bind(CorsHandler.class).asEagerSingleton();
        bind(BannerPublisherService.class);
        bind(JsonUtil.class).asEagerSingleton();
        bind(UploadFileService.class).asEagerSingleton();
        bind(Aws.class).asEagerSingleton();
    }
}
