package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by kleistit on 14/05/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushBanner {


    @Getter
    @Setter
    private String img_url;

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
