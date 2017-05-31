package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Banner extends AbstratEntity {

    @Getter
    @Setter
    private Integer width;
    @Getter
    @Setter

    private Integer height;
    @Getter
    @Setter
    private Integer clicks;
    @Setter
    @Getter
    private String picture;
    @Getter
    @Setter
    private String website_owner_token;

    @Builder.Default
    private List<Long> bannerSpaceIds = new ArrayList<>();


}
