package com.adscoop.publisher.services;

import lombok.extern.slf4j.Slf4j;
import ratpack.service.Service;
import ratpack.service.StartEvent;

import javax.inject.Inject;

@Slf4j
public class SchedulerTimerService implements Service {


    @Inject
    public SchedulerTimerService() {

    }

    @Override
    public void onStart(StartEvent event) throws Exception {


    }

}
