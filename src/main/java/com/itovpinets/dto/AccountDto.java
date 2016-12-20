package com.itovpinets.dto;

import com.itovpinets.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class AccountDto implements Serializable {
    private Long id;

    private String name;

    private BigDecimal deposit;

    private String description;

    private boolean isOuter;

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
        this.setId(newBookingDto.getId());
        this.setName(newBookingDto.getName());
        this.setDeposit(newBookingDto.getDeposit());
        this.setDescription(newBookingDto.getDescription());
        this.setOuter(newBookingDto.isOuter());
    }

    public AccountDto(Account account) {
        this.setId(account.getId());
        this.setName(account.getName());
        this.setDeposit(account.getDeposit());
        this.setDescription(account.getDescription());
        this.setOuter(account.isOuter());
    }
}
