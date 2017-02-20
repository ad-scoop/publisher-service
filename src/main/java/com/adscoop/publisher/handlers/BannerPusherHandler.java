package com.adscoop.publisher.handlers;



import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.entites.BannerNode;
import com.adscoop.publisher.services.BannerNodeService;
import com.google.inject.Inject;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;
import ratpack.sse.ServerSentEvents;
import rx.Observable;


import java.io.IOException;
import java.time.Duration;
import java.time.Period;
import java.util.*;


import static ratpack.jackson.Jackson.json;
import static ratpack.stream.Streams.periodically;

/**
 * Created by thokle on 28/10/2016.
 */
public class BannerPusherHandler implements Handler  {

    private  static Logger logger = LoggerFactory.getLogger(BannerPusherHandler.class);


    private JsonUtil jsonUtil;
    private BannerNodeService bannerNodeService;
    private List<com.adscoop.publisher.entites.BannerNode> bannerNodes;


    @Inject
    public BannerPusherHandler(JsonUtil jsonUtil, BannerNodeService bannerNodeService) {
        this.bannerNodeService = bannerNodeService;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public void handle(Context ctx) throws Exception , IOException{
        String token = ctx.getPathTokens().get("name").toString();
        logger.debug("token"+ token);

        RxRatpack.promise(Observable.from(bannerList() ).filter(ba -> ba.getBannerSpaceToken().contains(ctx.getPathTokens().get("token").toString())).map(map -> jsonUtil.bannerString(map))).then( banners ->  {
        ctx.getResponse().contentType("application/octet-stream");
            Publisher<String> bannerPublisher = periodically(ctx,Duration.ofSeconds(2), ban -> ban < banners.size() ?  banners.get(ban):null);

            ServerSentEvents  serverSentEvents = ServerSentEvents.serverSentEvents(bannerPublisher, f -> {

              f.id(Objects::toString).event("Banner").event(banner ->  "Banner"+ banner);
            });
            logger.info("PUSHED TO STREAM= "+serverSentEvents.getPublisher().toString());
                ctx.render(serverSentEvents);

        });



    }



    private List<BannerNode> bannerList() throws Exception {
      Duration  duration =   Duration.ofSeconds(3);

         Optional<Iterable<BannerNode>> bannerNodes =   bannerNodeService.getListWithReserveredTokens();

        if(bannerNodes.isPresent()){
            bannerNodes.get().iterator().forEachRemaining(bannerNode ->  {
                getBannerNodes().add(bannerNode);
            });
        }


return getBannerNodes();
    }


    private List<BannerNode> getBannerNodes() {
        return bannerNodes;
    }



}
