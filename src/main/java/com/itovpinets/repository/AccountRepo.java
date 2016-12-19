package com.itovpinets.repository;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

import com.itovpinets.entity.Account;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account a SET a.name=:name, a.description = :description WHERE a.id=:id")
    void updateAccount(@Param("id") Long id, @Param("name") String name, @Param("description") String description) throws ConstraintViolationException;
}
