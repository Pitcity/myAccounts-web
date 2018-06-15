package com.itovpinets.dto;

import com.itovpinets.entity.Deal;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class DealDto {

    private String id = UUID.randomUUID().toString();
    private String buyerId;
    private String buyer; //name
    private String sellerId;
    private String seller; //name
    private long date;
    private String note;
    private BigDecimal sum;

    public DealDto(String id, String buyer, String seller, long date, String note, BigDecimal sum) {
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
        this.buyerId = deal.getBuyer().getId();
        this.seller = deal.getSeller().getName();
        this.sellerId = deal.getSeller().getId();
        this.date = deal.getDate();
        this.note = deal.getNote();
        this.sum = deal.getSum();
    }

    public String getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setId(String id) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
