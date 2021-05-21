package com.bezkoder.springjwt.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "cards")

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private long card_id;
    @Column(name = "card_name")
    private String cardname;
    @Column(name = "card_num")
    private String cardnum;
    @Column(name = "card_cvc")
    private String cardcvc;
    @Column(name = "card_date")
    private String carddate;
    @ManyToOne
    @JoinColumn(name = "card_user")
    private User user;
    
public Card(){}
    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getCardcvc() {
        return cardcvc;
    }

    public void setCardcvc(String cardcvc) {
        this.cardcvc = cardcvc;
    }

    public String getCarddate() {
        return carddate;
    }

    public void setCarddate(String strDate) {
        this.carddate = strDate;
    }

    public Card(String cardname, String cardnum, String cardcvc, String carddate) {
        this.cardname = cardname;
        this.cardnum = cardnum;
        this.cardcvc = cardcvc;
        this.carddate = carddate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCard_id() {
        return card_id;
    }

    public void setCard_id(long card_id) {
        this.card_id = card_id;
    }
}
