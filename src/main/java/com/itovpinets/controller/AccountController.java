package com.itovpinets.controller;

import com.google.gson.Gson;
import com.itovpinets.dto.AccountDto;
import com.itovpinets.entity.Account;
import com.itovpinets.repository.AccountRepo;
import com.itovpinets.service.AccountService;
import com.itovpinets.service.DealService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Controller
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    DealService dealService;

    @RequestMapping(value = "/")
    public String home() {
        System.out.println("Controller: Passing address..");
        return "/WEB-INF/index.jsp";
    }

    @RequestMapping(value = "addAcc", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addAccount(@RequestBody AccountDto accDto, BindingResult bindingResult) {
        System.out.println(accDto.getIsOuter() + "\n\n\n\n\n\n\n ");
        if (!(accountService.findByName(accDto.getName()) == null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account already exsists");
        }
        //todo: if (accountValidator.validateAccount(accDto));
        System.out.println(accDto.getIsOuter() + "\n\n\n\n\n\n\n ");
        Account acc = new Account(accDto);
        System.out.println(acc.getIsOuter() + "\n\n\n\n\n\n\n ");
        accountRepo.save(acc);
        //// TODO: 21.12.2016 accountRepo.findAllInner returns List
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(accountService.findAllInner()));
    }

    @RequestMapping(value = "getAccList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> listOfAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(accountService.findAllInner()));
    }

    @RequestMapping(value = "getEditAcc_{accid}")
    public ResponseEntity<String> getEditAcc(@PathVariable Long accid) {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(new AccountDto(accountRepo.findOne(accid))));
    }

    @RequestMapping(value = "updateAcc", method = RequestMethod.POST)
    public ResponseEntity<String> updateAcc(@RequestBody AccountDto accDto, BindingResult bindingResult) {
        try {
            accountRepo.updateAccount(accDto.getId(), accDto.getName(), accDto.getDescription());
        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account with such a name already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(accountService.getAccDto(accountRepo.findAll())));
    }

    @RequestMapping(value = "deleteAcc_{accid}")
    public ResponseEntity<String> deleteAcc(@PathVariable Long accid) {
        Account acc = accountRepo.findOne(accid);
        dealService.updateDeals(acc);
        if (dealService.getDealsForAccount(acc).isEmpty())
            accountRepo.delete(accid);
        //// TODO: 21.12.2016 update deal (delete deals connected to this acc)
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(accountService.getAccDto(accountRepo.findAll())));
    }
    /*
    private List<AccountDto> getAccountDto(){
        List<Account>  listAcc = accountRepo.findAll();
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
    }*/
}