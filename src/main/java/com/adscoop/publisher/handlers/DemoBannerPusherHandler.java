package com.adscoop.publisher.handlers;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;

import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Singleton;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 20/05/2017.
 */
@Singleton
public class DemoBannerPusherHandler implements Handler {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(DemoBannerPusherHandler.class);
    private BannerPusherCreatorService bannerPusherCreatorService;

    @Inject
    public DemoBannerPusherHandler(BannerPusherCreatorService bannerPusherCreatorService) {
        this.bannerPusherCreatorService = bannerPusherCreatorService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        log.debug("send");





    }

}
