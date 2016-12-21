package com.itovpinets.dto;

import com.itovpinets.entity.Deal;

import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class DealDto {

    private long id;
    private String buyer;
    private String seller;
    private String date;
    private String note;
    private BigDecimal sum;

    public DealDto(long id, String buyer, String seller, String date, String note, BigDecimal sum) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.date = date;
        this.note = note;
        this.sum = sum;
    }

    public DealDto() {

    }

    public DealDto(Deal deal) {
        this.id = deal.getId();
        this.buyer = deal.getBuyer().getName();
        this.seller = deal.getSeller().getName();
        this.date = deal.getDate();
        this.note = deal.getNote();
        this.sum = deal.getSum();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
