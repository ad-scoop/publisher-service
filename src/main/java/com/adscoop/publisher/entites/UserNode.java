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
public class UserNode extends Entity {

    private String firstname;

    private String middlename;


    private boolean isActivated;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private String token;

    @Labels
    private List<String> labels = new ArrayList<>();

    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_CREDITINFO")
    private Set<CreditInfo> creditInfos = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_COMPANY")
    private Set<Company> companyNodes = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "USER_HAS_ADDRESS")
    private Set<AddressNode> addressNodes = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "HAS_ACCOUNT_INFORMATION")
    private Set<AccountInformation> accountInformations = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "HAS_BANNER_NODES")
    private Set<BannerNode> bannerNodes = new HashSet<>();

    private boolean activated;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AddressNode> getAddressNodes() {
        return addressNodes;
    }

    public void setAddressNodes(Set<AddressNode> addressNodes) {
        this.addressNodes = addressNodes;
    }

    public Set<BannerNode> getBannerNodes() {
        return bannerNodes;
    }

    public void setBannerNodes(Set<BannerNode> bannerNodes) {
        this.bannerNodes = bannerNodes;
    }

    public void addCreditInfo(CreditInfo creditInfo) {
        this.creditInfos.add(creditInfo);
        creditInfo.getUserNodeSet().add(this);
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformations.add(accountInformation);
        accountInformation.getUserNodeSet().add(this);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setLabel(String s) {
        this.labels.add(s);
    }

    public void setCompanyNode(Company companyNode) {
        this.getCompanyNodes().add(companyNode);
        companyNode.getUserNodes().add(this);
    }

    public Set<Company> getCompanyNodes() {
        return companyNodes;
    }

    public void setCompanyNodes(Set<Company> companyNodes) {
        this.companyNodes = companyNodes;
    }

    public Set<CreditInfo> getCreditInfos() {
        return creditInfos;
    }

    public void setCreditInfos(Set<CreditInfo> creditInfos) {
        this.creditInfos = creditInfos;
    }

    public Set<AccountInformation> getAccountInformations() {
        return accountInformations;
    }

    public void setAccountInformations(Set<AccountInformation> accountInformations) {
        this.accountInformations = accountInformations;
    }

    public void addBanner(BannerNode bannerNode) {
        bannerNodes.add(bannerNode);
        bannerNode.getUserNodes().add(this);
    }

    public void addAddress(AddressNode addressNode) {
        addressNodes.add(addressNode);
        addressNode.getUserNodes().add(this);
    }

    public UserNode activated() {
        this.activated = true;
        return this;
    }


}
