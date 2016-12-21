package com.itovpinets.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Entity
public class Deal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Account buyer;

    @ManyToOne
    private Account seller;
    private String date;
    private String note;
    private BigDecimal sum;

    public Deal(Account buyer, Account seller, String note, BigDecimal sum, String date) {
        this.buyer = buyer;
        this.seller = seller;
        this.sum = sum;
        this.note = note;
        this.date = date;
    }

    public Deal() {

    }

    public String getDate() {
        return date;
    }

    public Account getBuyer() {
        return buyer;
    }

    public Account getSeller() {
        return seller;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getNote() {
        return note;
    }

    public long getId() {
        return id;
    }
}
