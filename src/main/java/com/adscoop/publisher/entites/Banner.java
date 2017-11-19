package com.adscoop.publisher.entites;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Relationship;

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

    @Getter
    @Setter
    private String position_h;

    @Getter
    @Setter
    private String position_v;



    @Getter
    @Setter
    @Relationship(type = "HAS_INFO")
    private List<BannerNodeInfo> bannerNodeInfos = new ArrayList<>();

    @Builder.Default
    private List<Long> bannerSpaceIds = new ArrayList<>();


    public void addBannerInfo(BannerNodeInfo bannerNodeInfo) {
        bannerNodeInfos.add(bannerNodeInfo);

    }



}
