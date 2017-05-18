package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by kleistit on 14/05/2017.
 */
@Builder
public class PushBanner {


    @Getter
    @Setter
    private StringBuffer img_url;

    @Setter
    @Getter
    private int width;

    @Setter
    @Getter
    private int height;

    @Setter
    @Getter
    private String updateUrl;
    @Setter
    @Getter
    private String positon_h;
    @Setter
    @Getter
    private String position_v;

    @Getter
    @Setter
    private String token;

   @Getter
   @Setter
    private String clickUrl;



}
