package com.itovpinets.repository;

import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Repository
public interface DealRepo extends JpaRepository<Deal, Long> {


    /*@Query("SELECT d FROM Deal d WHERE d.buyer = :id OR d.seller = : id")
    List<Deal> findDealsFroAcc(@Param("id") Long id) throws ConstraintViolationException;*/
}
