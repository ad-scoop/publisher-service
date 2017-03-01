package com.adscoop.publisher.services;


import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by thokle on 24/02/2017.
 */

public class SchedulerService  implements Service{

    private final static Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    @Override
    public void onStart(StartEvent event) throws Exception {
        if(!event.isReload()){
            LOG.info("Scheduler starter");
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


    }

    private void close(){

    }
}
