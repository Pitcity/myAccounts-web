package com.itovpinets.service;

import com.itovpinets.entity.Account;
import com.itovpinets.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public boolean depositIsChanged (Account acc, BigDecimal ammount){
        if (!acc.isOuter())
            if (ammount.multiply(BigDecimal.valueOf(-1.0)).compareTo(acc.getDeposit())>0)
                return false;
            else {
                accountRepo.delete(acc);
                Account accForUpdate = new Account(acc.getName(),acc.getDeposit().add(ammount),acc.getDescription());
                accountRepo.save(accForUpdate);
                return true;
            }

        else return true;
    }
}
