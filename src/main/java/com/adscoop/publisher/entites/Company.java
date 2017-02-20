package com.adscoop.publisher.entites;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by thokle on 01/10/2016.
 */
@NodeEntity
public class Company  extends Entity {



    private String companyname;
    private String companytype;

    private Long cvr;


    @Labels
    private List<String> labeles = new ArrayList<>();

    @Relationship(direction = "INCOMING", type = "USER_HAS_COMPANY")
    private Set<UserNode> userNodes = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING , type = "COMPANY_HAS_ADDRESS")
    private  Set<AddressNode> addressNodes = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING , type = "COMPANY_HAS_BRANCH")
    private Set<Branches> branches = new HashSet<>();


   @Relationship(direction = Relationship.OUTGOING, type = "COMPANY_HAS_WEBSITE")
   private Set<WebSiteNode> webSiteNodes = new HashSet<>();


    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }



    public Long getCvr() {
        return cvr;
    }

    public void setCvr(Long cvr) {
        this.cvr = cvr;
    }

    public List<String> getLabeles() {
        return labeles;
    }

    public void setLabeles(List<String> labeles) {
        this.labeles = labeles;
    }

   @JsonIgnore
    public Set<UserNode> getUserNodes() {

        return userNodes;
    }

    public void setUserNodes(Set<UserNode> userNodes) {
        this.userNodes = userNodes;
    }

    public void setLabel(String label) {
        this.labeles.add(label);
    }

    public   void addAddress(AddressNode addressNode){
        addressNodes.add(addressNode);
        addressNode.getCompanyNodes().add(this);
    }

    public void addBrandh(Branches branches){
        this.branches.add(branches);
        branches.getCompanyNodes().add(this);
    }

    public void addWebSite(WebSiteNode webSiteNode){
        this.webSiteNodes.add(webSiteNode);
        webSiteNode.getCompanyNodes().add(this);

    }

    public Set<AddressNode> getAddressNodes() {
        return addressNodes;
    }

    public void setAddressNodes(Set<AddressNode> addressNodes) {
        this.addressNodes = addressNodes;
    }

    public Set<Branches> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branches> branches) {
        this.branches = branches;
    }

    public Set<WebSiteNode> getWebSiteNodes() {
        return webSiteNodes;
    }

    public void setWebSiteNodes(Set<WebSiteNode> webSiteNodes) {
        this.webSiteNodes = webSiteNodes;
    }
}
