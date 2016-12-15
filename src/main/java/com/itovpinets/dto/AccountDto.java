package com.itovpinets.dto;

import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class AccountDto {
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
}
