package com.adscoop.publisher.jobs;


import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.BannerNode;
import com.adscoop.publisher.services.BannerNodeService;
import com.google.inject.Inject;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import rx.Observable;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ratpack.stream.Streams.periodically;

/**
 * Created by thokle on 28/10/2016.
 */

public class BannerPusherHandler  implements Handler {

    private static Logger logger = LoggerFactory.getLogger(BannerPusherHandler.class);


    private JsonUtil jsonUtil;
    private BannerNodeService bannerNodeService;
    private List<com.adscoop.publisher.entites.BannerNode> bannerNodes;





    @Override
    public void handle(Context ctx) throws Exception, IOException {
        BannerNodeService bannerNodeService = ctx.get(BannerNodeService.class);
        JsonUtil jsonUtil = ctx.get(JsonUtil.class);
        String token = ctx.getPathTokens().get("token").toString();
        logger.debug("token" + token);

        RxRatpack.promise(bannerNodeService.getListWithReserveredTokens().filter(bannerNode -> !bannerNode.getBannerSpaceToken().isEmpty() && bannerNode.getBannerSpaceToken().size()>0 && bannerNode.getBannerSpaceToken().contains(token) ).map(JsonUtil::bannerString))
        .then(b -> { ctx.getResponse().contentType("application/octet-stream");

            Publisher<String> bannerPublisher = periodically(ctx, Duration.ofSeconds(2), ban -> ban < b.size() ? b.get(ban) : null);

            ServerSentEvents serverSentEvents = ServerSentEvents.serverSentEvents(bannerPublisher, f -> {

                f.id(Objects::toString).event("Banner").event(banner -> "Banner" + banner);
            });
            logger.debug("PUSHED TO STREAM= " + serverSentEvents.getPublisher().toString());
            ctx.render(serverSentEvents);

        });


    }







    private List<BannerNode> getBannerNodes() {
        return bannerNodes;
    }


}
