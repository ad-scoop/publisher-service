package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.PushBanner;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;
import rx.Observable;

import java.util.List;

/**
 * Created by kleistit on 18/05/2017.
 */
public class BannerPusherCreatorService {

private Session  session;

    public Promise<List<PushBanner>> getPushbanners() {

        return  null;
    }

}
