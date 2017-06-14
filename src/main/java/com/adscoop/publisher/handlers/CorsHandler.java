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

        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,ctx.getRequest().getHeaders().get("Origin"));
    headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
    headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS , true);




        ctx.next();
    }

}

