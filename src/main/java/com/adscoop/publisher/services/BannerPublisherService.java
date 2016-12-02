package com.adscoop.publisher.services;

import com.adscoop.entiites.BannerNode;
import com.adscoop.entiites.UserNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by thokle on 23/11/2016.
 */
public class BannerPublisherService {

    private ConnectionSource connectionSource;


    @Inject
    public BannerPublisherService(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }


    public Collection<BannerNode> findAllBanners(){
      return   connectionSource.session().loadAll(BannerNode.class);

    }


    public Long save(UserNode bannerNode){
        connectionSource.session().save(bannerNode);
        return connectionSource.session().load(UserNode.class,bannerNode.getId()).getId();

    }


    public UserNode findUserNodeByToken(String token){
        return connectionSource.session().queryForObject(UserNode.class,"match (u ) return a as UserNode", Collections.EMPTY_MAP);

    }
}
