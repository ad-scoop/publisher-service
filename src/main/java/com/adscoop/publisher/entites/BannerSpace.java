package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thokle on 24/08/2016.
 */
@NodeEntity
public class BannerSpace extends Entity {


    private String userId;

    private String domain;

    private String positionSiteM;

    private String positionSiteL;

    private URL Url;

    private String uniqeToken;

    private Double price;

    private Integer lattiude;

    private Integer longitude;

    private List<Regions> regions;

    @Labels
    private List<String> labels = new ArrayList<>();


    @Relationship(type = "BELONGS_TO_WEBSITE", direction = Relationship.INCOMING)
    private Set<WebSiteNode> webSiteNodeSet = new HashSet<>();

    @Relationship(type = "BANNERSPACE_HAS_BANNERS", direction = Relationship.OUTGOING)
    private Set<BannerNode> bannerNodesbannerSpaces = new HashSet<>();


    @Relationship(type = "HAS_CATEGORIES", direction = Relationship.OUTGOING)
    private Set<Category> categories = new HashSet<>();

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPositionSiteM() {
        return positionSiteM;
    }

    public void setPositionSiteM(String positionSiteM) {
        this.positionSiteM = positionSiteM;
    }

    public String getPositionSiteL() {
        return positionSiteL;
    }

    public void setPositionSiteL(String positionSiteL) {
        this.positionSiteL = positionSiteL;
    }

    public URL getUrl() {
        return Url;
    }

    public void setUrl(URL url) {
        Url = url;
    }

    public String getUniqeToken() {
        return uniqeToken;
    }

    public void setUniqeToken(String uniqeToken) {
        this.uniqeToken = uniqeToken;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLattiude() {
        return lattiude;
    }

    public void setLattiude(Integer lattiude) {
        this.lattiude = lattiude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public List<Regions> getRegions() {
        return regions;
    }

    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setLabel(String label) {
        this.labels.add(label);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public Set<WebSiteNode> getWebSiteNodeSet() {
        return webSiteNodeSet;
    }

    public void setWebSiteNodeSet(Set<WebSiteNode> webSiteNodeSet) {
        this.webSiteNodeSet = webSiteNodeSet;
    }

    public Set<BannerNode> getBannerNodesbannerSpaces() {
        return bannerNodesbannerSpaces;
    }


    public Set<Category> getCategories() {
        return categories;
    }


    public void addCategory(Category category) {
        categories.add(category);
        category.getBannerSpaces().add(this);
    }


    public void addRegion(Regions regions) {
        getRegions().add(regions);
        regions.getBannerSpaces().add(this);

    }
}
