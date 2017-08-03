package com.adscoop.publisher.handlers;

import com.google.common.net.HttpHeaders;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;

/**
 * Created by thokle on 19/11/2016.
 */
public class CorsHandler implements Handler {


    @Override
    public void handle(Context ctx) throws Exception {
        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"*");
    headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");

    headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS , true);
    headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT,PATCH");
headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Cache-Control, withCredentials, Last-Event-ID ");



        ctx.next();
    }

}

