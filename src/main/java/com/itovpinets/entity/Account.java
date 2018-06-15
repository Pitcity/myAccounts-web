package com.itovpinets.entity;

import com.itovpinets.dto.AccountDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Entity
@Embeddable
public class Account {

    @Id
    private String id;

    @Column(unique = false)
    @NotNull
    private String name;

    @ManyToOne
    private User user;

    @NotNull
    private BigDecimal deposit;

    private String description;

    private Boolean isOuter;

    public Account(String name, BigDecimal deposit, String description) {
        this.name = name;
        this.deposit = deposit;
        this.description = description;
        this.isOuter = false;
        this.id = UUID.randomUUID().toString();
    }

    public Account(String id, String name, BigDecimal deposit, String description) {
        this.name = name;
        this.deposit = deposit;
        this.description = description;
        this.isOuter = false;
        this.id = id;
    }

    /*
    * Method for creating an outer account
    * */
    public Account(String name, String id) {
        this.setName(name);
        this.setId(id);
        this.setDeposit(new BigDecimal(0));
        this.setDescription("");
        this.setIsOuter(true);
    }

    public Account(AccountDto accDto) {
        this.setDeposit(accDto.getDeposit());
        this.setName(accDto.getName());
        this.setId(accDto.getId());
        this.setDescription(accDto.getDescription());
        this.setIsOuter(accDto.getIsOuter());
    }

    public Account() {
    }

    public String getId() {
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
        this.isOuter = outer;
    }
}