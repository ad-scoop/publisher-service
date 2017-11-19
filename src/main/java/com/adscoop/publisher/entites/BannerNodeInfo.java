package com.adscoop.publisher.entites;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigDecimal;


@Builder
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class BannerNodeInfo extends AbstratEntity {





    @Setter
    @Getter
    private BigDecimal longitude;

    @Setter
    @Getter
    private BigDecimal latitude;

    @Setter
    @Getter
    private String appVersion;

    @Setter
    @Getter
    private String appName;

    @Setter
    @Getter
    private String appCodeName;

    @Setter
    @Getter
    private String accuracy;

    @Getter
    @Setter
    private String campagin_name;

    @Setter
    @Getter
    private String userAgent;
    @Setter
    @Getter
    private int hardwareConcurrency;
    @Getter
    @Setter
    private boolean cookieEnabled;
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    private String platform;


}
