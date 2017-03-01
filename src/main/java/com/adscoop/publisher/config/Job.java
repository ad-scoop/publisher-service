package com.adscoop.publisher.config;

import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by thokle on 24/02/2017.
 */
@CronTrigger(cron = "0/5 * * * * ?")
public class Job extends org.knowm.sundial.Job {
    private  static Logger logger = LoggerFactory.getLogger(Job.class);
    @Override
    public void doRun() throws JobInterruptException {
        logger.debug("PING");
    }
}
