package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    private long id;

    @Getter
    @Setter
    private int width;
    @Getter
    @Setter
    private long clicks;
    @Getter
    @Setter
    private String picture;
    @Getter
    @Setter
    private int height;
    @Getter
    @Setter
    private long endDate;
    @Getter
    @Setter
    private int maxPricePrDay;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private long startDate;


}
