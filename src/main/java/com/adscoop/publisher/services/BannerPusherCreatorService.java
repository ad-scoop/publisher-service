package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.Campagin;
import com.adscoop.publisher.entites.PushBanner;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.*;


/**
 * Created by kleistit on 18/05/2017.
 */
@Singleton
@Slf4j
public class BannerPusherCreatorService {

    private Session session;

    @Inject
    public BannerPusherCreatorService(Session session) {
        this.session = session;
    }


    public Iterable<PushBanner> getBannersByCampagin(String name) {
         Collection<Campagin> banners = session.loadAll(Campagin.class, new Filter("name", ComparisonOperator.EQUALS, name));
            ArrayList<PushBanner> pushBanners = new ArrayList<>();
         banners.parallelStream().forEach( campagin -> {
             PushBanner pushBanner = new PushBanner();
             pushBanner.setName(campagin.getName());

             campagin.getBanners().stream().forEach(banner ->  {
                 if(canPush(banner)) {
                     pushBanner.setId(banner.getId());
                     pushBanner.setPicture(banner.getPicture());
                     pushBanner.setWidth(banner.getWidth());
                     pushBanner.setHeight(banner.getHeight());
                     pushBanner.setHeight(Optional.of(banner.getHeight()).orElse(100));
                     pushBanner.setWidth(Optional.of(banner.getWidth()).orElse(300));

                 }
             });
             pushBanners.add(pushBanner);
         } );


        Iterable<PushBanner> iterable = new ArrayList<>(pushBanners);


        return iterable;

    }


    public boolean canPush(Banner banner) {

            return true;

    }

    public Promise<List<PushBanner>> getByToken(String token) {
        Iterable<Campagin> campagins = session.query(Campagin.class, "MATCH (c:Campagin)-[:CAMPAGIN_HAS_BANNERS]->(b:Banner) WHERE c.token={token} RETURN c,b", Collections.singletonMap("token", token));
        List<PushBanner> pushBanners = new ArrayList<>();
        campagins.iterator().forEachRemaining(campagin -> {

            if (isEnded(campagin)) {
                campagin.getBanners().parallelStream().forEach(banner -> {
                    pushBanners.add(mapToPushBanner(banner));
                });
            }
        });

        return Promise.value(pushBanners);
    }


    private boolean isEnded(Campagin campagin) {
        boolean isActive = false;
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.now());
        if (new Date().after(new Date(campagin.getEndDate()))) {

        } else if (new Date(campagin.getStartDate()).after(new Date())) {
            isActive = true;
        }
        return isActive;
    }


    private static PushBanner mapToPushBanner(Banner banner) {
        return PushBanner.builder().build();


    }
}
