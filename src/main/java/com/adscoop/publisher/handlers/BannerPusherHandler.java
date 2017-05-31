package com.adscoop.publisher.handlers;


import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Inject;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.Streams;

import java.io.IOException;


/**
 * Created by thokle on 28/10/2016.
 */

public class BannerPusherHandler implements Handler {

    private static Logger logger = LoggerFactory.getLogger(BannerPusherHandler.class);
    private BannerPusherCreatorService bannerNodeService;


    @Inject
    public BannerPusherHandler(BannerPusherCreatorService bannerNodeService) {
        this.bannerNodeService = bannerNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getPathTokens().get("token").toString();
        logger.debug("token" + token);
        RxRatpack.observe(bannerNodeService.getPushbanners()).filter(f -> f.iterator().next().getWebsite_owner_token().equalsIgnoreCase(token)).forEach(banners -> {
            Publisher<Banner> bannerPushBanner = Streams.publish(banners);
            ServerSentEvents serverSentEvents = ServerSentEvents.serverSentEvents(bannerPushBanner, bannerEvent -> bannerEvent.id(banner -> String.valueOf(banner.getWebsite_owner_token())).event("Push Banner")
                    .data(banner -> JsonUtil.bannerString(bannerNodeService.mapToPUshbanner(banner))));
            ctx.render(serverSentEvents);
        });
    }
}

