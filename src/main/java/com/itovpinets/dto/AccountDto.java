package com.itovpinets.dto;

import com.itovpinets.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class AccountDto implements Serializable {
    private String id = UUID.randomUUID().toString();

    private String name;

    private BigDecimal deposit;

    private String description;

    private boolean isOuter;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsOuter(boolean outer) {
        isOuter = outer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsOuter() {
        return isOuter;
    }

    public AccountDto() {

    }

    public AccountDto(AccountDto newBookingDto) {
        this.setId(newBookingDto.getId());
        this.setName(newBookingDto.getName());
        this.setDeposit(newBookingDto.getDeposit());
        this.setDescription(newBookingDto.getDescription());
        this.setIsOuter(newBookingDto.getIsOuter());
    }

    public AccountDto(Account account) {
        this.setId(account.getId());
        this.setName(account.getName());
        this.setDeposit(account.getDeposit());
        this.setDescription(account.getDescription());
        this.setIsOuter(account.getIsOuter());
    }
}
