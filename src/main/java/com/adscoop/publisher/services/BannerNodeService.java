package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.Banner;
import rx.Observable;

/**
 * Created by kleistit on 20/02/2017.
 */
public interface BannerNodeService {


    Observable<Banner> getListWithReserveredTokens() throws Exception;

}
