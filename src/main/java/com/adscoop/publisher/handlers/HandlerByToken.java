package com.adscoop.publisher.handlers;

import com.adscoop.publisher.entites.PushBanner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.TransformablePublisher;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static ratpack.stream.Streams.periodically;

public class HandlerByToken implements Handler {

    private BannerPusherCreatorService bannerPusherCreatorService;

    @Inject
    public HandlerByToken(BannerPusherCreatorService bannerPusherCreatorService) {
        this.bannerPusherCreatorService = bannerPusherCreatorService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        RxRatpack.observe(this.bannerPusherCreatorService.getByToken(ctx.getPathTokens().get("token"))).subscribe(pushBanners -> {
            TransformablePublisher<List<PushBanner>> transformablePublisher = periodically(ctx, Duration.ofSeconds(10), integer -> pushBanners);
            ctx.render(ServerSentEvents.serverSentEvents(transformablePublisher, listEvent -> listEvent.id(Objects::toString).data(t -> Jackson.getObjectWriter(ctx).writeValueAsString(t))));
        });
    }
}
