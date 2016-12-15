package com.itovpinets.service;

import com.itovpinets.dto.DealDto;
import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import com.itovpinets.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Service
public class DealService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountService accountService;

    public Deal createDeal(DealDto dealDto) {
        Account seller = accountRepo.findByName(dealDto.getSeller());
        Account buyer = accountRepo.findByName(dealDto.getBuyer());
        if (accountService.depositIsChanged(buyer,dealDto.getSum().multiply(BigDecimal.valueOf(-1)))&& accountService.depositIsChanged(seller,dealDto.getSum()))
            return new Deal(buyer, seller, dealDto.getNote(), dealDto.getSum(), dealDto.getDate());
        return null;
    }

    public DealDto getDto(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setBuyer(deal.getBuyer().getName());
        dealDto.setSeller(deal.getSeller().getName());
        dealDto.setDate(deal.getDate());
        dealDto.setId(deal.getId());
        dealDto.setSum(deal.getSum());
        dealDto.setNote(deal.getNote());

        return dealDto;
    }
}
