package com.adscoop.publisher.config;


import com.adscoop.publisher.entites.PushBanner;
import com.google.gson.Gson;
import com.google.inject.Inject;

/**
 * Created by thokle on 27/11/2016.
 */
public class JsonUtil {

   private static Gson gson;

    @Inject
    public JsonUtil(Gson gson) {
        this.gson = gson;
    }


    public  String bannerString(PushBanner bannerNode) {

        return gson.toJson(bannerNode);


    }
}
