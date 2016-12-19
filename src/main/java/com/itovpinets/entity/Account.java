package com.itovpinets.entity;

import com.itovpinets.dto.AccountDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Entity
@Embeddable
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    @NotNull
    String name;

    @NotNull
    BigDecimal deposit;

    String description;

    boolean isOuter;

    public Account(String name, BigDecimal deposit, String description) {
        this.name = name;
        this.deposit = deposit;
        this.description = description;
        this.isOuter = false;
    }

    public Account(String name) {
        this.name = name;
        this.isOuter = true;
    }

    public Account(AccountDto accDto) {
        this.deposit = accDto.getDeposit();
        this.name = accDto.getName();
        this.description = accDto.getDescription();
        this.isOuter = accDto.isOuter();
    }

    public Account() {
    }

    public Long getId() {
        return id;
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

    public boolean equals(Object o) {
        Account account = (Account) o;
        return this.getName().equals(account.getName());
    }

    public String getName() {
        return name;
    }
}