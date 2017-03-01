package com.adscoop.publisher.entites;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by thokle on 07/11/2016.
 */
@NodeEntity
public class AccountInformation extends Entity {


    private Integer regnr;
    private Integer accountnr;
    private String bankname;
    private CardType cardType;
    @Relationship(type = "HAS_ACCOUNT_INFORMATION", direction = Relationship.INCOMING)
    private Set<UserNode> userNodeSet = new HashSet<>();


    public Integer getRegnr() {
        return regnr;
    }

    public void setRegnr(Integer regnr) {
        this.regnr = regnr;
    }

    public Integer getAccountnr() {
        return accountnr;
    }

    public void setAccountnr(Integer accountnr) {
        this.accountnr = accountnr;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Set<UserNode> getUserNodeSet() {
        return userNodeSet;
    }

    public void setUserNodeSet(Set<UserNode> userNodeSet) {
        this.userNodeSet = userNodeSet;
    }


    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
