package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.BannerNode;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;
import rx.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kleistit on 20/02/2017.
 */
public class BannerNodeServiceImpl   implements BannerNodeService {


    private Session session;

private List<BannerNode> list = new ArrayList<>();
    @Inject
    public BannerNodeServiceImpl(Session session) {
        this.session = session;
    }


    @Override
    public Observable<BannerNode> getListWithReserveredTokens() throws Exception {
        try {

            return Observable.from(getList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }


    }

    private List<BannerNode> getList(){

       Iterable<BannerNode> bannerNodes =  session.query(BannerNode.class, "match (b:BannerNode) return b limit 10000", Collections.EMPTY_MAP);

       bannerNodes.iterator().forEachRemaining( bannerNode ->  {
           if(!bannerNode.getBannerSpaceToken().isEmpty()) {
               list.add(bannerNode);
           }


       });

       return list;
    }


}
