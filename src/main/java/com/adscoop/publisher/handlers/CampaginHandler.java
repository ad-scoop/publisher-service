package com.adscoop.publisher.handlers;

import com.adscoop.publisher.services.BannerNodeService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

/**
 * Created by thokle on 20/12/2016.
 */
public class CampaginHandler  implements Handler{

BannerNodeService bannerNodeService;


@Inject
    public CampaginHandler(BannerNodeService bannerNodeService) {
        this.bannerNodeService = bannerNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        RxRatpack.promise(bannerNodeService.getListWithReserveredTokens()).then( bannerNodes ->  {

            bannerNodes.stream().forEach( bannerNode ->  {
                ctx.getResponse().send("application/json", bannerNode.getCounterUrl());

            });

        });
    }
}
