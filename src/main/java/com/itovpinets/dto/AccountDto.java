package com.itovpinets.dto;

import com.itovpinets.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class AccountDto implements Serializable {
    Long id;

    String name;

    BigDecimal deposit;

    String description;

    boolean isOuter;

    public void setId(Long id) {
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

    public void setOuter(boolean outer) {
        isOuter = outer;
    }

    public Long getId() {

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

    public boolean isOuter() {
        return isOuter;
    }

    public AccountDto() {

    }

    public AccountDto(AccountDto newBookingDto) {
        this.id = newBookingDto.id;
        this.name = newBookingDto.getName();
        this.deposit = newBookingDto.getDeposit();
        this.description = newBookingDto.getDescription();
        this.isOuter = newBookingDto.isOuter();
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.deposit = account.getDeposit();
        this.description = account.getDescription();
        this.isOuter = account.isOuter();
    }
}
