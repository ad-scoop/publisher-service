package com.adscoop.publisher.handlers;

import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.Streams;
import rx.RxReactiveStreams;
import rx.plugins.RxJavaObservableExecutionHook;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;
import java.util.Observable;

/**
 * Created by thokle on 20/05/2017.
 */

public class DemoBannerPusherHandler implements Handler {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(DemoBannerPusherHandler.class);
    private BannerPusherCreatorService bannerPusherCreatorService;

    @Inject
    public DemoBannerPusherHandler(BannerPusherCreatorService bannerPusherCreatorService) {
        this.bannerPusherCreatorService = bannerPusherCreatorService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        RxRatpack.promise(this.bannerPusherCreatorService.pushBannerObservable()).map( l -> {
            Iterable<PushBanner> pushBanners = null;

           Publisher<PushBanner> pushBannerPublisher = Streams.publish()




        });

    }

}
