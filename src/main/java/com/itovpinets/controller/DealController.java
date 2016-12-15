package com.itovpinets.controller;

import com.itovpinets.dto.AccountDto;
import com.itovpinets.dto.DealDto;
import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import com.itovpinets.repository.DealRepo;
import com.itovpinets.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Controller
public class DealController {

    @Autowired
    private DealRepo dealRepo;

    @Autowired
    DealService dealService;

    @RequestMapping (value = "/",method = RequestMethod.GET)
    public List<DealDto> listOfDealsForAcc(Account account) {
        List<Deal> listOfDeals = dealRepo.findBySellerOrBuyer(account);
        List<DealDto> listDealDto= new LinkedList<DealDto>();
        for (Deal deal: listOfDeals)
            listDealDto.add(dealService.getDto(deal));

        return listDealDto;
    }

    @RequestMapping (value = "/addDeal",method = RequestMethod.POST)
    public boolean addDeal(DealDto dealDto) {
        Deal newDeal = dealService.createDeal(dealDto);
        if (newDeal!=null) {
            dealRepo.save(newDeal);
            return true;
        } else
            return false;       //TODO:not enough money on acc
    }

    @RequestMapping (value = "/deleteDeal{dealId}")
    public void deleteAcc(Long dealId) {
        dealRepo.delete(dealRepo.findOne(dealId));
    }

}
