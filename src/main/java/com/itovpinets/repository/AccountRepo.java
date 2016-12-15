package com.itovpinets.repository;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

import com.itovpinets.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
        Account findByName(String name);
}
