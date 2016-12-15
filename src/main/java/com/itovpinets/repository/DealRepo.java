package com.itovpinets.repository;

import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public interface DealRepo extends JpaRepository<Deal,Long> {
        List<Deal> findBySellerOrBuyer(Account account);
}
