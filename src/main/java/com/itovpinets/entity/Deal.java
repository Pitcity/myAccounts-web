package com.itovpinets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Entity
public class Deal implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Account buyer;

    @ManyToOne
    private Account seller;
    private long date;
    private String note;
    private BigDecimal sum;

    public Deal(Account buyer, Account seller, String note, BigDecimal sum, long date) {
        this.buyer = buyer;
        this.seller = seller;
        this.sum = sum;
        this.note = note;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    public Deal() {

    }

    public long getDate() {
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

    public String getId() {
        return id;
    }
}
