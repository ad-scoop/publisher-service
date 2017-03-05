package com.adscoop.publisher.services;


import com.adscoop.publisher.entites.BannerNode;
import com.google.inject.Inject;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.exceptions.JobInterruptException;

import org.neo4j.ogm.session.Session;
import org.quartz.core.Scheduler;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Timer;

/**
 * Created by thokle on 24/02/2017.
 */

public class SchedulerService  implements Service{

    private  Session session;

    @Inject
    public SchedulerService(Session session) {
    this.session = session;
    }

    private final static Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    @Override
    public void onStart(StartEvent event) throws Exception {
        if(!event.isReload()){
           session.query(BannerNode.class, "match (b:BannerNode) return b", Collections.EMPTY_MAP).iterator().forEachRemaining(  o -> {

               LOG.debug("OBJECT "+ o.toString());

           });

init();
        } else {
            LOG.info("Restart on start");
        }
    }


    @Override
    public void onStop(StopEvent event) throws Exception {
        if(!event.isReload()){
            LOG.debug("Scheduler stop");
            close();
        }else{

        }
    }




    private  void  init(){
        LOG.debug("statt timer");
      Timer timer = new Timer();
      BannerNodeServiceImpl bannerNodeService  = new BannerNodeServiceImpl(session);
      timer.schedule(bannerNodeService,1
      );
LOG.debug("end timer");

    }

    private void close(){
    SundialJobScheduler.shutdown();
    }
}
