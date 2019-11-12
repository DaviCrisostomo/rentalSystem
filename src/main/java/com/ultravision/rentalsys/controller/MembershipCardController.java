package com.ultravision.rentalsys.controller;

import com.ultravision.rentalsys.model.Customer.AccessLevel;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.repository.MembershipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@ComponentScan
@Controller
public class MembershipCardController {

    @Autowired
    MembershipCardRepository cardRepository;

    public MembershipCard getById(Long customerId){

        return cardRepository.findByCustomerId(customerId);

    }

    public void accLevelUpgrade(MembershipCard card, AccessLevel newAccLevel) {

        if(card.getAccLevel().getCost()>newAccLevel.getCost())
            System.out.println("It doesnt make any sense...");

        else{
            card.payment(newAccLevel.getCost());
            card.setAccLevel(newAccLevel);
            card.setLastUpdate();
            cardRepository.save(card);
            System.out.println("Your Subscription Has Been Upgraded to "+newAccLevel.getAccess()+".");
        }
    }

    public void checkingData(String data){

        String[] details = data.split(",");

        if(details.length!=2)
            System.out.println("Wrong number of values");
        else if(details[0].matches("[A-Z][a-z]*"))
            System.out.println("Wrong value for field id");
        else if(!AccessLevel.compareString(details[1]))
            System.out.println("Wrong value for field AccessLevel");
        else{
            accLevelUpgrade(cardRepository.findByCustomerId(Long.parseLong(details[0])),AccessLevel.valueOf(details[1].toUpperCase().trim()));
        }

    }

    public boolean checkIfSubsciptionHasExpired(MembershipCard card){

        LocalDate today = LocalDate.now();
        //Checking if subscription plan has expired
        //if the lastUpdate happened more than one moth ago
        //change plan to Regular Customer
        if(card.getAccLevel()!=AccessLevel.RC && today.isAfter(card.getlastUpdate().plusMonths(1))) {
            card.setAccLevel(AccessLevel.RC);
            card.setLastUpdate(today);
            return true;
        }
        else
            return false;

    }

}
