package com.itovpinets.service;

import com.itovpinets.dto.AccountDto;
import com.itovpinets.entity.Account;
import com.itovpinets.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;

    public boolean depositIsChanged(Account acc, BigDecimal ammount) {
        if (!acc.isOuter())
            if (ammount.multiply(BigDecimal.valueOf(-1.0)).compareTo(acc.getDeposit()) > 0)
                return false;
            else {
                accountRepo.delete(acc);
                Account accForUpdate = new Account(acc.getName(), acc.getDeposit().add(ammount), acc.getDescription());
                accountRepo.save(accForUpdate);
                return true;
            }

        else return true;
    }

    public Account findByName(String name) {
        List<Account> listOfAccs = accountRepo.findAll();
        for (Account acc : listOfAccs) {
            if (acc.getName().equals(name))
                return acc;
        }
        return null;
    }

    public List<AccountDto> getAccDto(List<Account> listAccs) {
        List<AccountDto> listOfAccsDto = new LinkedList<>();
        for (Account acc : listAccs) {
            listOfAccsDto.add(new AccountDto(acc));
        }
        return listOfAccsDto;
    }

    public boolean accountIsAdded(AccountDto accDto) {
        if (!(findByName(accDto.getName()) == null)) {
            return false;
        }
        //todo: if (accountValidator.validateAccount(accDto));
        Account acc = new Account(accDto);
        accountRepo.save(acc);
        return true;
    }

}
