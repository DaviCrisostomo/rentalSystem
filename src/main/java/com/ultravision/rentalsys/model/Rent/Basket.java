package com.ultravision.rentalsys.model.Rent;

import com.ultravision.rentalsys.model.Customer.AccessLevel;
import com.ultravision.rentalsys.model.Customer.CanRent;
import com.ultravision.rentalsys.model.Customer.MembershipCard;
import com.ultravision.rentalsys.model.Items.Item;
import com.ultravision.rentalsys.model.Titles.BoxSet;
import com.ultravision.rentalsys.model.Titles.Movie;
import com.ultravision.rentalsys.model.Titles.Music;
import com.ultravision.rentalsys.model.Titles.Title;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Basket {

    private CanRent canRent;

    private Set <Item> basket = new LinkedHashSet<>();

    public Basket(AccessLevel level){

        this.canRent = setBasket(level);

    }

    private CanRent setBasket(AccessLevel access) {
      //implementing the functional interface:
        switch(access.getAccess()){

            case "Regular Customer":
                return (Title i) -> false;//pays regular renting value for all items

            case "Music Lover":
                return (Title i) -> i instanceof Music;//only audio cd and live concert are free

            case "TV Lover":
                return (Title i) -> i instanceof BoxSet;//only tv box set items are free

            case "Video Lover":
                return (Title i) -> i instanceof Movie;//only movies are free

            default:
                return (Title i) -> true;//whatever the customer wants

        }

    }

    private boolean addItem(Item item){

        if(checkingPermission(item)){
            basket.add(item);
            return true;
        }

        return false;
    }

    public boolean checkingPermission(Item item){

        return canRent.canInclude(item.getTitle());
    }
}