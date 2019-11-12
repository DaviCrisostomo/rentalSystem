package com.ultravision.rentalsys.model.Customer;

//functional interface: Contains only one method

import com.ultravision.rentalsys.model.Titles.Title;

public interface CanRent {

    //the method will define what kind of item
//is available for rent considering the
//customer access level.
    boolean canInclude(Title i);

}
