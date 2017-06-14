package com.adscoop.publisher.handlers;


import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Inject;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.Streams;
import rx.Observable;
import rx.Scheduler;

import java.time.Duration;
 import static ratpack.stream.Streams.periodically;


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

        Observable<Banner> bannerObservable =  this.bannerNodeService.pushBannerObservable();

        RxRatpack.promise(bannerObservable).operation( banners  -> banners.parallelStream().forEach(pushBanner -> {
         long start =    System.currentTimeMillis();
            logger.debug("send banner " + pushBanner);
            Publisher<Banner> pushBannerPublisher = periodically(ctx,Duration.ofSeconds(50), i -> i < banners.size() ? pushBanner : null);

        ServerSentEvents serverSentEvents  = ServerSentEvents.serverSentEvents(pushBannerPublisher, pushBannerEvent -> pushBannerEvent.id(Object::toString).data(pushBanner1 -> Jackson.getObjectWriter(ctx).writeValueAsString(pushBanner1)));
        int hasCode =  serverSentEvents.hashCode();

            ctx.render(serverSentEvents);
            long end = System.currentTimeMillis();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("timer "+ String.valueOf( end - start));

        }));

    }
}

