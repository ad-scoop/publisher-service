package com.adscoop.publisher.entites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;


/**
 * Created by thokle on 09/10/2016.
 */
@NodeEntity
public class FootPrintInformationNode extends Entity {

    @Labels
    private List<String> list = new ArrayList<>();

    @Relationship(type = "BELONGS_TO_BANNER",direction = Relationship.INCOMING)
    private   List<BannerNode> bannerNodeSet = new ArrayList<>();



    private Map<String, Object> information = new HashMap<>();


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<BannerNode> getBannerNodeSet() {
        return bannerNodeSet;
    }

    public void setBannerNodeSet(List<BannerNode> bannerNodeSet) {
        this.bannerNodeSet = bannerNodeSet;
    }

    public Map<String, Object> getInformation() {
        return information;
    }

    public void setInformation(Map<String, Object> information) {
        this.information = information;
    }

    public void setLabel(String s) {

        this.list.add(s);
    }
}
