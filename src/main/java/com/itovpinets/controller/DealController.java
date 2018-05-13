package com.itovpinets.controller;

import com.google.gson.Gson;
import com.itovpinets.dto.DealDto;
import com.itovpinets.entity.Deal;
import com.itovpinets.repository.AccountRepo;
import com.itovpinets.repository.DealRepo;
import com.itovpinets.service.AccountService;
import com.itovpinets.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Controller
public class DealController {

    @Autowired
    private DealRepo dealRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    DealService dealService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "dealsFroAcc_{id}", method = RequestMethod.GET)
    public ResponseEntity<String> listOfDealsForAcc(@PathVariable Long id) {
        List<DealDto> listOfDeals = dealService.getDealsForAccount(accountRepo.findOne(id));
        if (listOfDeals.isEmpty())
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there's no deals for this acc");
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(listOfDeals));
    }

    @RequestMapping(value = "addDeal", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addDeal(@RequestBody DealDto dealDto) {
        Deal newDeal = dealService.createDeal(dealDto);
        if (newDeal != null) {
            dealRepo.save(newDeal);
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(newDeal)); //TODO:what does it means ok?
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not enough money on buyer's acc");
        //TODO:not enough money on acc
    }

}
