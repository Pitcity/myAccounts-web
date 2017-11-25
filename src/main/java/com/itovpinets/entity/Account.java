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
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @NotNull
    private BigDecimal deposit;

    private String description;

    private Boolean isOuter;

    public Account(String name, BigDecimal deposit, String description) {
        this.name = name;
        this.deposit = deposit;
        this.description = description;
        this.isOuter = false;
    }

    public Account(Long id, String name, BigDecimal deposit, String description) {
        this.name = name;
        this.deposit = deposit;
        this.description = description;
        this.isOuter = false;
        this.id = id;
    }

    public Account(String name) {
        this.setName(name);
        this.setDeposit(new BigDecimal(0));
        this.setDescription("");
        this.setIsOuter(true);
    }

    public Account(AccountDto accDto) {
        this.setDeposit(accDto.getDeposit());
        this.setName(accDto.getName());
        this.setDescription(accDto.getDescription());
        this.setIsOuter(accDto.getIsOuter());
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

    public boolean getIsOuter() {
        return isOuter;
    }

    public boolean equals(Object o) {
        Account account = (Account) o;
        return this.getName().equals(account.getName());
    }

    public String getName() {
        return name;
    }

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

    public void setIsOuter(boolean outer) {
        this.isOuter = outer;
    }
}