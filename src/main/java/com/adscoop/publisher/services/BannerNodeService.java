package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.BannerNode;
import rx.Observable;

/**
 * Created by kleistit on 20/02/2017.
 */
public interface BannerNodeService {


    Observable<BannerNode> getListWithReserveredTokens() throws Exception;

}
