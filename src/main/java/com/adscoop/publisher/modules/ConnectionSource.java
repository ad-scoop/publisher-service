package com.adscoop.publisher.modules;

import java.io.IOException;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import com.google.inject.Inject;


public class ConnectionSource {


    SessionFactory sessionFactory;


    @Inject
    public ConnectionSource(Config config) throws IOException {
        sessionFactory = new SessionFactory(configuration(config), "com.adscoop.publisher.entites");
        configuration(config);
    }

    private Configuration configuration(Config config) throws IOException {
        Configuration configuration = new Configuration.Builder().uri(config.getNeo4Url()).credentials(config.getUsername(),config.getPassword()).build();

        return configuration;

    }

    public Session session() {

        return sessionFactory.openSession();

    }


}
