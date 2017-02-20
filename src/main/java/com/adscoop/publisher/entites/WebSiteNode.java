package com.adscoop.publisher.entites;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by thokle on 18/10/2016.
 */
@NodeEntity
public class WebSiteNode extends Entity {


    private int port;
    private String hostname;
    private String path;

    @Relationship(type = "WEBSITE_HAS_BANNERSPACE", direction = Relationship.OUTGOING)
    private Set<BannerSpace> bannerSpaceSet = new HashSet<>();


    @Relationship(type ="COMPANY_HAS_WEBSITE ", direction = Relationship.INCOMING)
    private  Set<Company> companyNodes = new HashSet<>();


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<BannerSpace> getBannerSpaceSet() {
        return bannerSpaceSet;
    }

    public void setBannerSpaceSet(Set<BannerSpace> bannerSpaceSet) {
        this.bannerSpaceSet = bannerSpaceSet;
    }

    @JsonIgnore
    public Set<Company> getCompanyNodes() {
        return companyNodes;
    }

    public void setCompanyNodes(Set<Company> companyNodes) {
        this.companyNodes = companyNodes;
    }

    public void addBannerSpace(BannerSpace bannerSpace){
        bannerSpaceSet.add(bannerSpace);
        bannerSpace.getWebSiteNodeSet().add(this);
    }
}
