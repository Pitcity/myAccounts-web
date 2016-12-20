package com.itovpinets.controller;

import com.google.gson.Gson;
import com.itovpinets.dto.AccountDto;
import com.itovpinets.dto.DealDto;
import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import com.itovpinets.repository.AccountRepo;
import com.itovpinets.repository.DealRepo;
import com.itovpinets.service.AccountService;
import com.itovpinets.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

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

    /*@RequestMapping (value = "/",method = RequestMethod.GET)
    public List<DealDto> listOfDealsForAcc(Account account) {
        List<Deal> listOfDeals = dealService.dealRepo.findAll();
        List<DealDto> listDealDto= new LinkedList<DealDto>();
        //for (Deal deal: listOfDeals)
            //listDealDto.add(dealService.getDto(deal));

        return listDealDto;
    }*/

    @RequestMapping (value = "addDeal",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addDeal(@RequestBody DealDto dealDto) {
        Deal newDeal = dealService.createDeal(dealDto);
        if (newDeal!=null) {
            dealRepo.save(newDeal);
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(newDeal)); //TODO:what does it means ok?
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not enough money on buyer's acc");
        //TODO:not enough money on acc
    }

    /*@RequestMapping (value = "/deleteDeal{dealId}")
    public void deleteAcc(Long dealId) {
        dealService.dealRepo.delete(dealService.dealRepo.findOne(dealId));
    }*/

}
