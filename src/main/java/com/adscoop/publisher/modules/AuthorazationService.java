package com.adscoop.publisher.modules;


import com.adscoop.publisher.entites.UserNode;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by thokle on 04/12/2016.
 */
public class AuthorazationService {

    private Session session;


    @Inject
    public AuthorazationService(Session session) {
        this.session = session;

    }


    public Optional<UserNode> login(String username, String password) {
        String res = null;

        UserNode userNode = session.queryForObject(UserNode.class, " match (u) where u.email='" + username + "' and  u.password='" + password + "'   return u", Collections.emptyMap());
        return Optional.ofNullable(userNode);

    }


    public Optional<UserNode> tokenExist(String token) {
        Optional<UserNode> userNode = Optional.empty();

        userNode = Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where  u.token='" + token + "' return u", Collections.emptyMap()));

        return userNode;
    }

}
