package com.itovpinets.repository;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

import com.itovpinets.entity.Account;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, String> {

    @Modifying
    @Query("UPDATE Account a SET a.name=:name, a.description = :description WHERE a.id=:id")
    void updateAccount(@Param("id") String id, @Param("name") String name, @Param("description") String description) throws ConstraintViolationException;

    @Query("SELECT a FROM Account a WHERE a.name=:name")
    Account findByName(@Param("name") String name) throws ConstraintViolationException;

    @Modifying
    @Query("UPDATE Account a SET a.isOuter=:isOuter WHERE a.id=:id")
    void changeOuter(@Param("id") String id, @Param("isOuter") boolean isOuter) throws ConstraintViolationException;

    @Modifying
    @Query("UPDATE Account a SET a.name=:name, a.description = :description, a.deposit = :deposit WHERE a.id=:id")
    void updateAccountWithDeposit(@Param("id") String id, @Param("name") String name, @Param("description") String description, @Param("deposit") BigDecimal deposit) throws ConstraintViolationException;

}
