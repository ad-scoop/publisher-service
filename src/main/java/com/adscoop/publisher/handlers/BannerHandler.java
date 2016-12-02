package com.adscoop.publisher.handlers;


import com.adscoop.entiites.BannerNode;
import com.adscoop.publisher.config.JsonUtil;
import com.adscoop.publisher.services.BannerPublisherService;
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
import java.util.*;


import static ratpack.jackson.Jackson.json;
import static ratpack.stream.Streams.periodically;

/**
 * Created by thokle on 28/10/2016.
 */
public class BannerHandler implements Handler  {

    private  static Logger logger = LoggerFactory.getLogger(BannerHandler.class);

    private BannerPublisherService bannerPublisherService;
    private JsonUtil jsonUtil;

    @Inject
    public BannerHandler(BannerPublisherService bannerPublisherService, JsonUtil jsonUtil) {
        this.bannerPublisherService = bannerPublisherService;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public void handle(Context ctx) throws Exception , IOException{
        String token = ctx.getPathTokens().get("name").toString();
        logger.debug("token"+ token);

        RxRatpack.promise(Observable.from(mocklist() ).filter(ba -> ba.getBannerSpaceToken().equals(ctx.getPathTokens().get("name").toString())).map(map -> jsonUtil.bannerString(map))).then( banners ->  {
        ctx.getResponse().contentType("");
            Publisher<String> bannerPublisher = periodically(ctx,Duration.ofSeconds(2), ban -> ban < banners.size() ?  banners.get(ban):null);

            ServerSentEvents  serverSentEvents = ServerSentEvents.serverSentEvents(bannerPublisher, f -> {

              f.id(Objects::toString).event("Banner").event(banner ->  "Banner"+ banner);
            });
            logger.info("PUSHED TO STREAM= "+serverSentEvents.getPublisher().toString());
                ctx.render(serverSentEvents);

        });



    }


    private List<BannerNode> mocklist(){
        List<BannerNode> bannerNodes = new ArrayList<>();

        for(int i = 0;i< 10; i++){
            bannerNodes.add(createBannerNode());
        }
return bannerNodes;

    }


    private BannerNode createBannerNode(){
        BannerNode bannerNode = new BannerNode();

        bannerNode.setDomain("Dom");
        bannerNode.setBannerSpaceToken("212312312312312312");
        bannerNode.setCounter(new Integer(123));
        bannerNode.setHeight(new Integer(12322));
        return bannerNode;


    }

    private List<BannerNode> bannerList(){


        Collection<BannerNode> bannerNodes = bannerPublisherService.findAllBanners();
        List<BannerNode> bannerNodes1 = new ArrayList<>();
        bannerNodes.stream().filter(filter -> filter !=null).forEach(bannerNode -> {
            bannerNodes1.add(bannerNode); }
        );

         return bannerNodes1;
    }
}
