package com.adscoop.publisher.handlers;

import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import com.google.inject.Singleton;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;



import ratpack.sse.ServerSentEvents;
import static ratpack.sse.ServerSentEvents.serverSentEvents;

import rx.RxReactiveStreams;

import javax.inject.Inject;

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
