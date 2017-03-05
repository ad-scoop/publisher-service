package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.BannerNode;
import com.google.inject.Inject;
import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by kleistit on 20/02/2017.
 */
@CronTrigger(cron = "0/5 * * * * ?")
public class BannerNodeServiceImpl  extends TimerTask implements BannerNodeService {
private  static Logger logger = LoggerFactory.getLogger(BannerNodeServiceImpl.class);

    private Session session;



    private List<BannerNode> list = new ArrayList<>();

    @Inject
    public BannerNodeServiceImpl(Session session) {
        super();
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

    @Override
    public void run() {

        getList();
    }

    private List<BannerNode> getList(){

       Iterable<BannerNode> bannerNodes =  session.query(BannerNode.class, "match (b:BannerNode) return b limit 10000", Collections.EMPTY_MAP);

       bannerNodes.iterator().forEachRemaining( bannerNode ->  {
           if(bannerNode.getBannerSpaceToken() !=null) {
               list.add(bannerNode);
           }


       });

       return list;
    }


}
