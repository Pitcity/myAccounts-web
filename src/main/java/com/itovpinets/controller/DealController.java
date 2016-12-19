package com.itovpinets.controller;

import com.itovpinets.entity.Account;
import com.itovpinets.repository.AccountRepo;
import com.itovpinets.repository.DealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */

@Controller
public class DealController {

   /* @Autowired
    private DealRepo dealRepo;

    @Autowired
    private AccountRepo accountRepo;*/

   /* @Autowired
    DealService dealService;

    @Autowired
    AccountService accountService;
*/
   /* @RequestMapping (value = "/",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        accountRepo.save(new Account("Ihor", BigDecimal.valueOf(1000), "ihor's account"));
        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "index";

    }*/

    /*@RequestMapping (value = "/",method = RequestMethod.GET)
    public List<DealDto> listOfDealsForAcc(Account account) {
        List<Deal> listOfDeals = dealService.dealRepo.findAll();
        List<DealDto> listDealDto= new LinkedList<DealDto>();
        //for (Deal deal: listOfDeals)
            //listDealDto.add(dealService.getDto(deal));

        return listDealDto;
    }
    @RequestMapping (value = "/addDeal",method = RequestMethod.POST)
    public boolean addDeal(DealDto dealDto) {
        Deal newDeal = dealService.createDeal(dealDto);
        if (newDeal!=null) {
            dealService.dealRepo.save(newDeal);
            return true;
        } else
            return false;       //TODO:not enough money on acc
    }

    @RequestMapping (value = "/deleteDeal{dealId}")
    public void deleteAcc(Long dealId) {
        dealService.dealRepo.delete(dealService.dealRepo.findOne(dealId));
    }
*/
}
