package com.adscoop.publisher.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;


/**
 * Created by thokle on 24/08/2016.
 */
@NodeEntity(label = "Category")
public class Category extends Entity {


    @Labels
    private List<String> labels = new ArrayList<>();


    @Relationship(type = "BELONGS_TO_BANNER",direction = Relationship.INCOMING)
    private Set<BannerNode> catbannerNodes = new HashSet<>();

   @Relationship(type = "BELONGS_TO_BANNERSPACE",direction = Relationship.INCOMING)
   private Set<BannerSpace> catBannerSpaces = new HashSet<>();

    private String name;
    private String value;


    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String s){
        this.labels.add(s);
    }


    public Set<BannerNode> getCatbannerNodes() {
        return catbannerNodes;
    }

    public void setCatbannerNodes(Set<BannerNode> catbannerNodes) {
        this.catbannerNodes = catbannerNodes;
    }

    public Set<BannerSpace> getBannerSpaces() {
        return catBannerSpaces;
    }

    public void setBannerSpaces(Set<BannerSpace> bannerSpaces) {
        this.catBannerSpaces = bannerSpaces;
    }
}
