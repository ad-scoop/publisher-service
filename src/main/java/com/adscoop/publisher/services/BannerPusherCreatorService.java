package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.PushBanner;

import org.neo4j.ogm.session.Session;

import ratpack.exec.Promise;
import javax.inject.Inject;

import java.util.Collections;


/**
 * Created by kleistit on 18/05/2017.
 */

public class BannerPusherCreatorService {

private Session  session;

    @Inject
    public BannerPusherCreatorService(Session session) {
        this.session = session;
    }

    public Promise<Iterable<Banner>> getPushbanners() {
               return Promise.async( downstream -> {
                Iterable<Banner> banners =  session.query(Banner.class,"match (b:Banner) return b", Collections.emptyMap());

                 downstream.success(banners);
                });
    }

    public PushBanner mapToPUshbanner(Banner m){
        PushBanner pushBanner =new PushBanner();
        pushBanner.setWidth(m.getWidth());
        pushBanner.setImg_url(m.getPicture());

     return pushBanner;
    }
}
