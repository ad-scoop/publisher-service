package com.adscoop.publisher.jobs;


import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Inject;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.TransformablePublisher;

import java.io.IOException;
import java.time.Duration;

import static ratpack.stream.Streams.periodically;


/**
 * Created by thokle on 28/10/2016.
 */

public class BannerPusherHandler  implements Handler {

    private static Logger logger = LoggerFactory.getLogger(BannerPusherHandler.class);



    private BannerPusherCreatorService bannerNodeService;


    @Inject
    public BannerPusherHandler(JsonUtil jsonUtil, BannerPusherCreatorService bannerNodeService) {
        this.jsonUtil = jsonUtil;
        this.bannerNodeService = bannerNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception, IOException {

        String token = ctx.getPathTokens().get("token").toString();
        logger.debug("token" + token);
        RxRatpack.observe(bannerNodeService.getPushbanners()).forEach( pushBanners -> {
            pushBanners.stream().filter(f -> f.getToken().equals(ctx.getPathTokens().get("token"))).forEach( pushBanner -> {
                Publisher<String>  bannerTransformablePublisher = periodically(ctx, Duration.ofSeconds(2), ban -> ban < pushBanners.size() ? pushBanners.get(ban) : null);
                ServerSentEvents serverSentEvents = ServerSentEvents.serverSentEvents(bannerTransformablePublisher, pushBannerEvent -> {
                    pushBannerEvent.data(b -> b);

                });
                ctx.render(serverSentEvents);
            });


        });


          /**
        RxRatpack.promise(bannerNodeService.getListWithReserveredTokens().filter(bannerNode -> !bannerNode.getToken().isEmpty() && bannerNode.getToken().length()>0 && bannerNode.getToken().contains(token)).forEach( b -> {
            Publisher<String> bannerPublisher = periodically(ctx, Duration.ofSeconds(2), ban -> ban < b.size() ? b.get(ban) : null);

            ServerSentEvents serverSentEvents = ServerSentEvents.serverSentEvents(bannerPublisher, f -> {

                f.id(Objects::toString).event("Banner").event("counter").data(banner ->   banner);
            });
            logger.debug("PUSHED TO STREAM= " + b);
            ctx.render(serverSentEvents);

        }); */




    }










}
