package com.adscoop.publisher.handlers;

import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.services.BannerPusherCreatorService;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.Streams;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

import static ratpack.sse.ServerSentEvents.serverSentEvents;
import static ratpack.stream.Streams.periodically;

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
        Publisher<String> stream = periodically(ctx, Duration.ofMillis(5), i ->
                i < 5 ? i.toString() : null
        );

        ServerSentEvents events = serverSentEvents(stream, e ->
                e.id(Objects::toString).event("counter").data(i -> "event " + i.toString())
        );

        log.debug(events.getPublisher().toString());
        log.debug("DEMO DEMO DEMO");
         ctx.render(events);

    }

}
