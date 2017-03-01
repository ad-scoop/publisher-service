package com.adscoop.publisher.entites;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by thokle on 24/08/2016.
 */

@NodeEntity
public class CreditInfo extends Entity {


    @Relationship(type = "USER_HAS_CREDITINFO", direction = Relationship.INCOMING)
    Set<UserNode> userNodeSet = new HashSet<>();
    @Labels
    private List<String> labels = new ArrayList<>();
    private String type;
    private String cardHolderName;
    private String cardnumber;
    private Integer startDate;
    private Integer startEndYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }


    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getStartEndYear() {
        return startEndYear;
    }

    public void setStartEndYear(Integer startEndYear) {
        this.startEndYear = startEndYear;
    }


    public Set<UserNode> getUserNodeSet() {
        return userNodeSet;
    }

    public void setLabel(String s) {
        this.labels.add(s);
    }

    public List<String> getLabels() {
        return labels;
    }

    public String getCardnumber() {
        return cardnumber;
    }


    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
}
