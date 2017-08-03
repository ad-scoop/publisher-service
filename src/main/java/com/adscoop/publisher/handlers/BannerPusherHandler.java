package com.adscoop.publisher.handlers;


import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Inject;

import ratpack.exec.Promise;
import ratpack.exec.util.ParallelBatch;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.TransformablePublisher;
import rx.Observable;


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

String name= ctx.getPathTokens().get("name");

        RxRatpack.publisher(Observable.just(this.bannerNodeService.getBannersByCampagin(name))).multicast().toPromise().then((banners -> {

            if(banners != null) {
                TransformablePublisher<Iterable<PushBanner>> pub = ParallelBatch.of(Promise.value(banners)).publisher();

                ctx.render(ServerSentEvents.serverSentEvents(pub, tEvent -> tEvent.id(Objects::toString).data(t -> Jackson.getObjectWriter(ctx).writeValueAsString(t))));
                logger.debug("has sent");
                Thread.sleep(10000);

            }

        }));


    }
}

