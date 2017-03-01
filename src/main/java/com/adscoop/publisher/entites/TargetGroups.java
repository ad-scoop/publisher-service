package com.adscoop.publisher.entites;

import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.util.*;

/**
 * Created by thokle on 25/08/2016.
 */
public class TargetGroups extends Entity {

    @Labels
    private List<String> labels = new ArrayList<>();


    private Map<String, Object> targetGroups = new HashMap<>();

    @Relationship(direction = Relationship.INCOMING, type = "TARGET_BELONGS_TO_BANNERSPACE")
    private Set<BannerSpace> tarbannerSpaceSet = new HashSet<>();


    @Relationship(direction = Relationship.INCOMING, type = "TARGET_BELONGS_TO_BANNER")
    private Set<BannerNode> tarbannerNodes = new HashSet<>();


    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Set<BannerSpace> getTarbannerSpaceSet() {
        return tarbannerSpaceSet;
    }

    public void setTarbannerSpaceSet(Set<BannerSpace> tarbannerSpaceSet) {
        this.tarbannerSpaceSet = tarbannerSpaceSet;
    }

    public Set<BannerNode> getTarbannerNodes() {
        return tarbannerNodes;
    }

    public void setTarbannerNodes(Set<BannerNode> tarbannerNodes) {
        this.tarbannerNodes = tarbannerNodes;
    }

    public void addTargerVariable(String key, Object value) {

        targetGroups.put(key, value);
    }
}
