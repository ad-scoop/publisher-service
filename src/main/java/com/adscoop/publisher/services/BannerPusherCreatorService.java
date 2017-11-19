package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import com.adscoop.publisher.entites.Campagin;
import com.adscoop.publisher.entites.PushBanner;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
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
        Map<String, String> map = new HashMap<>();
        List<PushBanner> pushBanners = new ArrayList<>();
        map.put("name", name);
        Result banners = session.query("MATCH (c:Campagin)-[:CAMPAGIN_HAS_BANNERS]->(b:Banner) WHERE c.name={name} RETURN b,c AS n", map);


        banners.queryResults().iterator().forEachRemaining(stringObjectMap -> stringObjectMap.forEach((s, o) -> {
            PushBanner pushBanner = new PushBanner();
            if (o instanceof Campagin) {
                Campagin campagin = ((Campagin) o);
                pushBanner.setClicks(campagin.getClicks());
                pushBanner.setEndDate(campagin.getEndDate());
                pushBanner.setStartDate(campagin.getStartDate());
                pushBanner.setName(Optional.of(campagin.getName()).orElse("no name"));
                log.debug("Instance of Campagin");
            } else if (o instanceof Banner) {
                Banner banner = ((Banner) o);
                pushBanner.setId(banner.getId());
                pushBanner.setPicture(banner.getPicture());
                pushBanner.setHeight(Optional.of(banner.getHeight()).orElse(300));
                pushBanner.setWidth(Optional.of(banner.getWidth()).orElse(600));
                log.debug("Instance of Banner");
            }
            pushBanners.add(pushBanner);
        }));

        Iterable<PushBanner> iterable = new ArrayList<>(pushBanners);
        return iterable;

    }

    public Promise<Iterable<Banner>> getBannersByTimer() {
        log.info("getBannerByTimer");


        return Promise.async(downstream -> {


        });


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
