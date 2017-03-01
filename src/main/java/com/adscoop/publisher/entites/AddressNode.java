package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AddressNode extends Entity {


    @JsonIgnore
    @Relationship(direction = Relationship.INCOMING, type = "COMPANY_HAS_ADDRESS")
    Set<Company> companyNodes = new HashSet<>();
    @JsonIgnore
    @Relationship(direction = Relationship.INCOMING, type = "USER_HAS_ADDRESS")
    Set<UserNode> userNodes = new HashSet<>();
    private String streetname;
    private Integer streetNumber;
    private Integer floor;
    private String site;
    private Integer zipcode;
    private String city;
    private String region;
    private String country;
    @Labels
    private List<String> labels = new ArrayList<>();

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Set<UserNode> getUserNodes() {
        return userNodes;
    }

    public void setUserNodes(Set<UserNode> userNodes) {
        this.userNodes = userNodes;
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


    @JsonIgnore
    public Set<Company> getCompanyNodes() {
        return companyNodes;
    }

    public void setCompanyNodes(Set<Company> companyNodes) {
        this.companyNodes = companyNodes;
    }
}
