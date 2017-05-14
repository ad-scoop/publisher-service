package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import com.google.inject.Inject;

import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.*;

/**
 * Created by kleistit on 20/02/2017.
 */
public class BannerNodeServiceImpl   implements BannerNodeService {
private  static Logger logger = LoggerFactory.getLogger(BannerNodeServiceImpl.class);

    private Session session;



    private List<Banner> list = new ArrayList<>();

    @Inject
    public BannerNodeServiceImpl(Session session) {
        super();
        this.session = session;
    }


    @Override
    public Observable<Banner> getListWithReserveredTokens() throws Exception {
        try {

            return Observable.from(getList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }



    }



    private List<Banner> getList(){

       Iterable<Banner> bannerNodes =  session.query(Banner.class, "match (b:BannerNode) return b limit 10000", Collections.EMPTY_MAP);

       bannerNodes.iterator().forEachRemaining( bannerNode ->  {

       });
return  null;

    }



    private  boolean validIsExpired(int startday, int startMonth, int startYear, int endday, int endMonth ,int endyear){


return true;

    }
}
