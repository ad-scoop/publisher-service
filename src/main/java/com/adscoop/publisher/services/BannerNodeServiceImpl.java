package com.adscoop.publisher.services;

import com.adscoop.publisher.entites.BannerNode;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by kleistit on 20/02/2017.
 */
public class BannerNodeServiceImpl implements  BannerNodeService {


    private Session session;


    @Inject
    public BannerNodeServiceImpl(Session session) {
        this.session = session;
    }



    @Override
    public Optional<Iterable<BannerNode>> getListWithReserveredTokens() throws Exception {
        try{

            return Optional.ofNullable(session.query(BannerNode.class,"match (b:BannerNode) return b", Collections.emptyMap()));
        }catch (Exception e){
            throw  new Exception(e.getMessage());

        }


    }
}
