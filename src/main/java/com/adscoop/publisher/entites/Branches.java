package com.adscoop.publisher.entites;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by thokle on 01/10/2016.
 */
@NodeEntity
public class Branches extends Entity {

    private String name;

    @Labels
    private List<String> labels = new ArrayList<>();


    private String branchId;

    @Relationship(direction = Relationship.OUTGOING, type = "BRANCH_HAS_ADDRESS")
    private Set<AddressNode> addressNodeList = new HashSet<>();

    @Relationship(direction = Relationship.INCOMING, type = "BRANCH_BELONGS_TO")
    private Set<Company> companyNodes = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "CATEGORIES")
    private Set<Category> categories = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Set<AddressNode> getAddressNodeList() {
        return addressNodeList;
    }

    public void setAddressNodeList(Set<AddressNode> addressNodeList) {
        this.addressNodeList = addressNodeList;
    }

    public Set<Company> getCompanyNodes() {
        return companyNodes;
    }

    public void setCompanyNodes(Set<Company> companyNodes) {
        this.companyNodes = companyNodes;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
