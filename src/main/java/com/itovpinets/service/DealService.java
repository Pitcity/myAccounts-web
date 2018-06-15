package com.itovpinets.service;

import com.itovpinets.dto.DealDto;
import com.itovpinets.entity.Account;
import com.itovpinets.entity.Deal;
import com.itovpinets.repository.AccountRepo;
import com.itovpinets.repository.DealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IhorTovpinets on 15.12.2016.
 */
@Service
public class DealService {

    @Autowired
    public AccountRepo accountRepo;

    @Autowired
    public DealRepo dealRepo;

    @Autowired
    AccountService accountService;

    public Deal createDeal(DealDto dealDto) {
        Account seller = accountRepo.findOne(dealDto.getSellerId());
        Account buyer = accountRepo.findOne(dealDto.getBuyerId());
        if (seller == null) {
            seller = accountRepo.findByName(dealDto.getSeller());
            if (seller == null) {
                accountRepo.save(new Account(dealDto.getSeller(), dealDto.getSellerId()));
                seller = accountRepo.findOne(dealDto.getSellerId());
            }
        }
        if (buyer == null) {
            buyer = accountRepo.findByName(dealDto.getBuyer());
            if (buyer == null) {
                accountRepo.save(new Account(dealDto.getBuyer(), dealDto.getBuyerId()));
                buyer = accountRepo.findOne(dealDto.getBuyerId());
            }
        }

        if (accountService.depositIsChanged(buyer, dealDto.getSum().multiply(BigDecimal.valueOf(-1))) &&
                accountService.depositIsChanged(seller, dealDto.getSum())) {
            return new Deal(buyer, seller, dealDto.getNote(), dealDto.getSum(), dealDto.getDate());
        }
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

    public List<DealDto> getDealsDtoList(List<Deal> listOfDeals) {
        List<DealDto> listOfDealsDto = new LinkedList<DealDto>();
        for (Deal d : listOfDeals) {
            listOfDealsDto.add(new DealDto(d));
        }
        return listOfDealsDto;
    }

    public List<DealDto> getDealsForAccount(Account account) {
        List<Deal> dealList = dealRepo.findAll();
        List<DealDto> dealListDto = new LinkedList<>();
        for (Deal d : dealList) {
            if (account.equals(d.getBuyer()) || account.equals(d.getSeller())) {
                dealListDto.add(new DealDto(d));
            }
        }
        return dealListDto;
    }

    public void updateDeals(Account accountToDelete) {
        accountRepo.changeOuter(accountToDelete.getId(), true);
        List<Deal> dealList = dealRepo.findAll();
        for (Deal d : dealList)
            if (d.getBuyer().getIsOuter() && d.getSeller().getIsOuter())
                dealRepo.delete(d);
    }
}
