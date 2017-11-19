package com.adscoop.publisher.handlers;

import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import ratpack.exec.Promise;
import ratpack.exec.util.ParallelBatch;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.TransformablePublisher;
import rx.Observable;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Objects;

import static ratpack.stream.Streams.periodically;



@Slf4j
public class TimerHandler implements Handler {

    private BannerPusherCreatorService bannerPusherCreatorService;

    @Inject
    public TimerHandler(BannerPusherCreatorService bannerPusherCreatorService) {
        this.bannerPusherCreatorService = bannerPusherCreatorService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

    RxRatpack.publisher(Observable.just(bannerPusherCreatorService.getBannersByCampagin(ctx.getPathTokens().get("name")))).multicast().toPromise().then(pushBanners -> {

          TransformablePublisher<Iterable<PushBanner>> iterableTransformablePublisher =  periodically(ctx, Duration.ofSeconds(Integer.valueOf(ctx.getPathTokens().get("sec"))) , integer -> pushBanners);

            ctx.render(ServerSentEvents.serverSentEvents(iterableTransformablePublisher, tEvent -> tEvent.id(Objects::toString).data(t -> Jackson.getObjectWriter(ctx).writeValueAsString(t))));
            log.debug("has sent");



        });

    }
}
