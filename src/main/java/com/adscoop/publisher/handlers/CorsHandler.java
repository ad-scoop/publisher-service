package com.adscoop.publisher.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;

/**
 * Created by thokle on 19/11/2016.
 */
public class CorsHandler implements Handler {

    private static Logger logger = LoggerFactory.getLogger(CorsHandler.class);

    @Override
    public void handle(Context ctx) throws Exception {
        logger.debug("Cors handler");
        MutableHeaders mutableHeaders = ctx.getResponse().getHeaders();

        mutableHeaders.set("Access-Control-Allow-Origin", "*");
        mutableHeaders.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
        mutableHeaders.set("Content-Type", "text/event-stream");

        mutableHeaders.set("Connection", "keep-alive");


        ctx.next();
    }
}

