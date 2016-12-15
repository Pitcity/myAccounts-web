package com.itovpinets.controller;

import com.itovpinets.dto.AccountDto;
import com.itovpinets.entity.Account;
import com.itovpinets.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @RequestMapping (value = "/",method = RequestMethod.GET)
    public List<AccountDto> listOfAccounts() {
        List<Account>  listAcc= accountRepo.findAll();
        List<AccountDto> listAccDto= new LinkedList<AccountDto>();
        for (Account acc: listAcc) {
            AccountDto accDto = new AccountDto();
            accDto.setName(acc.getName());
            accDto.setDeposit(acc.getDeposit());
            accDto.setDescription(acc.getDescription());
            accDto.setId(acc.getId());
            accDto.setOuter(acc.isOuter());

            listAccDto.add(accDto);
        }
        return listAccDto;
    }

    @RequestMapping (value = "/addAcc", method = RequestMethod.POST)
    public boolean addAcc(AccountDto accDto) {
        if(accountRepo.findByName(accDto.getName())!=null) {
            return false; //TODO:account already exists
        } else {
            Account acc = new Account(accDto);
            accountRepo.save(acc);
            return true;
        }
    }

    @RequestMapping (value = "/delete{accId}")
    public void deleteAcc(Long accid) {
        accountRepo.delete(accountRepo.findOne(accid));
    }
}
