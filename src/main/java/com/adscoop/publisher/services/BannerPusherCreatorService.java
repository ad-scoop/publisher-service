package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.PushBanner;
import com.google.inject.Singleton;
import org.neo4j.ogm.session.Session;
import rx.Observable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by kleistit on 18/05/2017.
 */
@Singleton
public class BannerPusherCreatorService {

    private Session session;

    @Inject
    public BannerPusherCreatorService(Session session) {
        this.session = session;
    }



    public Observable<Banner> pushBannerObservable() {
           return  Observable.interval(1,TimeUnit.SECONDS).map(aLong -> {
                     Banner banner = new Banner();
                     banner.setClicks(21);
                     banner.setHeight(100);

                     return banner;

             });

    }

    public Observable<PushBanner> getPushBanners(){

        Iterable<Banner>  banners = session.query(Banner.class, "match (b:Banner) return b", Collections.emptyMap());
        List<PushBanner> pushBanners = new ArrayList<>();

        banners.iterator().forEachRemaining( banner -> {
            PushBanner pushBanner = new PushBanner();
            pushBanner.setImg_url(banner.getPicture());
            pushBanner.setWidth(banner.getWidth());
            pushBanner.setHeight(banner.getHeight());
            pushBanners.add(pushBanner);

        });

        Observable<PushBanner> val = Observable.from(pushBanners);
return val;

    }



    public  Observable<Banner> bannerObservable(){
        return Observable.interval(1, TimeUnit.SECONDS).map( aLong -> {

            Banner banner = new Banner();
            banner.setPicture("url");

return banner;
        });
    }


    public PushBanner mapToPUshbanner(Banner m) {
        PushBanner pushBanner = new PushBanner();
        pushBanner.setWidth(m.getWidth());
        pushBanner.setImg_url(m.getPicture());

        return pushBanner;
    }
}
