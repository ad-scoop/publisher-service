package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.BannerNode;

import java.util.Optional;

/**
 * Created by kleistit on 20/02/2017.
 */
public interface BannerNodeService {


    Optional<Iterable<BannerNode>> getListWithReserveredTokens() throws Exception;

}