package com.adscoop.publisher.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.neo4j.ogm.session.Session;

import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.PushBanner;
import com.google.inject.Singleton;


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


    public  Iterable<Banner> banners(){

        return  session.query(Banner.class, "MATCH (b:Banner) return b limit 5",Collections.emptyMap());

    }

    public List<PushBanner> pushBanners() {
        Iterable<Banner> banners = session.query(Banner.class, "MATCH (b:Banner) RETURN b LIMIT 5", Collections.emptyMap());

        List<PushBanner> pushBanners = new ArrayList<>();
        banners.iterator().forEachRemaining(banner -> {
            PushBanner pushBanner = new PushBanner();
            pushBanner.setHeight(banner.getHeight());
            pushBanner.setWidth(banner.getWidth());

        });
        return pushBanners;
    }


    public Iterable<PushBanner> getBannersByCampagin(String name){
        Map<String, String> map = new HashMap<>();

        map.put("name",name);
       Iterable<Banner> banners = session.query(Banner.class, "MATCH (c:Campagin)-[:CAMPAGIN_HAS_BANNERS]->(b:Banner) where c.name={name} return b", map);
       List<PushBanner> pushBanners = new ArrayList<>();

       banners.iterator().forEachRemaining(banner -> {
           PushBanner pushBanner = new PushBanner();

           pushBanner.setClickUrl("/click/"+banner.getId());
           pushBanner.setUpdateUrl("/update"+banner.getId());
            pushBanner.setImg_url(banner.getPicture());

           pushBanners.add(pushBanner);
       });


        Iterable<PushBanner> iterable = new ArrayList<>(pushBanners);

        return iterable;

    }


}
